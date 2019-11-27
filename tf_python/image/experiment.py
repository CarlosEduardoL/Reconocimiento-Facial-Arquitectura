import time
from image.LBP import LBP

print('Staring')
with open('data.csv', 'a+')as file:
    file.write('replica, tamanho,algoritmo,localidad,tiempo\n')
    file.flush()
    for repetition in range(0, 110):
        rep_str = str(repetition)
        for running in range(1, 5):
            run_str = rep_str + ',' + str(running)
            lbp = LBP('../images/level' + str(running) + '.png')
            lbp.lbp_image_his_8_1ij()

            t = time.time()
            lbp.lbp_image_his_8_1ij()
            file.write(run_str + ',8_1,si,' + str(time.time() - t) + '\n')
            file.flush()
            t = time.time()
            lbp.lbp_image_his_8_2ij()
            file.write(run_str + ',8_2,si,' + str(time.time() - t) + '\n')
            file.flush()
            t = time.time()
            lbp.lbp_image_his_16_2ij()
            file.write(run_str + ',16_2,si,' + str(time.time() - t) + '\n')
            file.flush()

            t = time.time()
            lbp.lbp_image_his_8_1ji()
            file.write(run_str + ',8_1,no,' + str(time.time() - t) + '\n')
            file.flush()
            t = time.time()
            lbp.lbp_image_his_8_2ji()
            file.write(run_str + ',8_2,no,' + str(time.time() - t) + '\n')
            file.flush()
            t = time.time()
            lbp.lbp_image_his_16_2ji()
            file.write(run_str + ',16_2,no,' + str(time.time() - t) + '\n')
            file.flush()
        print('running N°', repetition)
print('finalice')
