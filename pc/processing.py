import io

import pyautogui
from PIL import Image


async def get_screen_point(left, top, right, bottom):
    screenshot = pyautogui.screenshot(region=(left + 10,
                                              top + 40,
                                              right - left - 20,
                                              bottom - top - 10 - 40))
    screenshot_hd = screenshot.resize((right - left - 20, bottom - top - 10), Image.NEAREST)
    with io.BytesIO() as output:
        screenshot_hd.save(output, format='JPEG')
        return output.getvalue()
