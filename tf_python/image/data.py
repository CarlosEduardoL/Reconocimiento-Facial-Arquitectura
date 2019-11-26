with open('data_final.csv', 'w+') as file:
    for repetition in range(0, 105):
        with open('data' + str(repetition) + '.csv', 'r+')as f:
            lines = f.readlines()
            if repetition == 0:
                file.write('replica,'+lines[0])
                file.flush()
            for i in range(1, len(lines)):
                file.write(str(repetition)+','+lines[i])
                file.flush()
