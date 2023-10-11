target_bytes = file_to_bytes("bg10_1687509901291_X248_Y5.png")
    background_bytes = file_to_bytes("normalbg10_1687509901291_X248_Y5.png")
    target = cv2.imdecode(np.frombuffer(target_bytes, np.uint8), cv2.IMREAD_ANYCOLOR)
    target_y = 0
    target_x = 0

    background = cv2.imdecode(np.frombuffer(background_bytes, np.uint8), cv2.IMREAD_ANYCOLOR)

    background = cv2.Canny(background, 100, 200)
    target = cv2.Canny(target, 100, 200)

    background = cv2.cvtColor(background, cv2.COLOR_GRAY2RGB)
    target = cv2.cvtColor(target, cv2.COLOR_GRAY2RGB)

    res = cv2.matchTemplate(background, target, cv2.TM_CCOEFF_NORMED)
    min_val, max_val, min_loc, max_loc = cv2.minMaxLoc(res)
    h, w = target.shape[:2]
    bottom_right = (max_loc[0] + w, max_loc[1] + h)
    print([int(max_loc[0]), int(max_loc[1]), int(bottom_right[0]), int(bottom_right[1])])