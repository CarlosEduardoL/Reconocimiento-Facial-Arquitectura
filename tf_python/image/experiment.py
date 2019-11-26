import time
from image.LBP import LBP

print('Staring')
for repetition in range(0, 105):
    with open('data' + str(repetition) + '.csv', 'a+')as file:
        file.write('tamanho,algoritmo,localidad,tiempo\n')
        file.flush()
        for running in range(1, 5):
            lbp = LBP('../images/level' + str(running) + '.png')
            lbp.lbp_image_his_8_1ij()

            t = time.time()
            lbp.lbp_image_his_8_1ij()
            file.write(str(running) + ',8_1,si,' + str(time.time() - t) + '\n')
            file.flush()
            t = time.time()
            lbp.lbp_image_his_8_2ij()
            file.write(str(running) + ',8_2,si,' + str(time.time() - t) + '\n')
            file.flush()
            t = time.time()
            lbp.lbp_image_his_16_2ij()
            file.write(str(running) + ',16_2,si,' + str(time.time() - t) + '\n')
            file.flush()

            t = time.time()
            lbp.lbp_image_his_8_1ji()
            file.write(str(running) + ',8_1,no,' + str(time.time() - t) + '\n')
            file.flush()
            t = time.time()
            lbp.lbp_image_his_8_2ji()
            file.write(str(running) + ',8_2,no,' + str(time.time() - t) + '\n')
            file.flush()
            t = time.time()
            lbp.lbp_image_his_16_2ji()
            file.write(str(running) + ',16_2,no,' + str(time.time() - t) + '\n')
            file.flush()
    print('running N°',repetition)
print('finalice')