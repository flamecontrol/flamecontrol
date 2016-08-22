import random

response_time = 0
for i in range(10):
    run_cost = random.randint(0, 100)
    print(run_cost)
    response_time = run_cost if run_cost > response_time else response_time
print(response_time)