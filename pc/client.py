import asyncio

import pygetwindow
import vgamepad as vg
import websockets
import win32gui
import yaml

from processing import get_screen_point

with open('config.yaml', 'r') as file:
    config = yaml.safe_load(file)

token = config['database']['token']
window_title = config['database']['window']
api = config['database']['api']

gamepad = vg.VDS4Gamepad()
button_map = {
    'up': vg.DS4_DPAD_DIRECTIONS.DS4_BUTTON_DPAD_NORTH,
    'left': vg.DS4_DPAD_DIRECTIONS.DS4_BUTTON_DPAD_WEST,
    'down': vg.DS4_DPAD_DIRECTIONS.DS4_BUTTON_DPAD_SOUTH,
    'right': vg.DS4_DPAD_DIRECTIONS.DS4_BUTTON_DPAD_EAST,
    'a': vg.DS4_BUTTONS.DS4_BUTTON_CROSS,
    'b': vg.DS4_BUTTONS.DS4_BUTTON_CIRCLE,
    'x': vg.DS4_BUTTONS.DS4_BUTTON_SQUARE,
    'y': vg.DS4_BUTTONS.DS4_BUTTON_TRIANGLE,
    'l1': vg.DS4_BUTTONS.DS4_BUTTON_SHOULDER_LEFT,
    'l2': vg.DS4_BUTTONS.DS4_BUTTON_THUMB_LEFT,
    'r1': vg.DS4_BUTTONS.DS4_BUTTON_SHOULDER_RIGHT,
    'r2': vg.DS4_BUTTONS.DS4_BUTTON_THUMB_RIGHT,
    'rr': vg.DS4_BUTTONS.DS4_BUTTON_OPTIONS,
    'll': vg.DS4_BUTTONS.DS4_BUTTON_SHARE,
}


async def send_binary_data():
    async with websockets.connect(f"{api}/imageSocket?token={token}") as websocket:
        while True:
            active_window = pygetwindow.getActiveWindowTitle()
            if active_window == window_title:
                hwnd = win32gui.GetForegroundWindow()
                client = win32gui.GetClientRect(hwnd)
                window = win32gui.GetWindowRect(hwnd)
                left, top, right, bottom = window[0] - client[0], window[1] - client[1], window[2], window[3]
                frame_bytes = await get_screen_point(left, top, right, bottom)
                try:
                    await websocket.send(frame_bytes)
                except asyncio.IncompleteReadError as e:
                    print("Incomplete read error:", e)


async def get_movement():
    async with websockets.connect(f"{api}/movement?token={token}") as websocket:
        while True:
            response = await websocket.recv()
            await get_move(response)


async def get_move(command):
    action = command.split('_')[0]
    key = command.split('_')[1]
    print(command)
    if action == 'm':
        gamepad.press_button(button=button_map[key])
        gamepad.update()
    elif action == 's':
        gamepad.release_button(button=button_map[key])
        gamepad.update()
    elif action == 'd':
        gamepad.directional_pad(direction=button_map[key])
        gamepad.update()
    elif action == 'ds':
        gamepad.directional_pad(direction=vg.DS4_DPAD_DIRECTIONS.DS4_BUTTON_DPAD_NONE)
        gamepad.update()
    elif action == 'sp':
        gamepad.press_special_button(vg.DS4_SPECIAL_BUTTONS.DS4_SPECIAL_BUTTON_PS)
    elif action == 'sps':
        gamepad.release_special_button(vg.DS4_SPECIAL_BUTTONS.DS4_SPECIAL_BUTTON_PS)


tasks = [send_binary_data(), get_movement()]
asyncio.get_event_loop().run_until_complete(asyncio.wait(tasks))
