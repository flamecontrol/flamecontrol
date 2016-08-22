import time


def set_log(level, *args):
    log_level = 2
    DEBUG = 3
    INFO = 2
    ERROR = 1

    time_log = time.strftime("%H:%M:%S")
    if level == DEBUG:
        print(time_log, ":[DEBUG]:", *args)
    if level == INFO:
        print(time_log, ":[INFO]:", *args)
    if level == ERROR:
        print(time_log, ":[ERROR]:", *args)

set_log(1, 1)
set_log(2, 2, "aaa")
set_log(3, 3, "aaa", "aaa")