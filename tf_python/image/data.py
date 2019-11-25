with open('data_final.csv', 'w+') as file:
    for repetition in range(0, 105):
        with open('data' + str(repetition) + '.csv', 'r+')as f:
            lines = f.readlines()
            if repetition == 0:
                file.write(lines[0])
                file.flush()
            for line in lines:
                file.write(line)
                file.flush()
