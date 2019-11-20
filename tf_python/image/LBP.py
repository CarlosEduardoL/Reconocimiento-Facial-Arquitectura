import numpy as np
from skimage import io
import time


class LBP:

    def __init__(self, image_route):
        self.image_route = image_route
        self.image = np.array(io.imread(image_route, as_gray=True) * 255, dtype=np.uint8)
        self.__convert = 1 << np.array([i for i in range(7, -1, -1)], dtype=np.uint8)
        self.mask8_1 = np.array([[1, 2, 4],
                                 [128, 0, 8],
                                 [64, 32, 16]])
        self.mask8_2 = np.array([[1, 0, 2, 0, 4],
                                 [0, 0, 0, 0, 0],
                                 [128, 0, 0, 0, 8],
                                 [0, 0, 0, 0, 0],
                                 [64, 0, 32, 0, 16]])
        self.mask16_2 = np.array([[1 << 0, 1 << 1, 1 << 2, 1 << 3, 1 << 4],
                                  [1 << 15, 0, 0, 0, 1 << 5],
                                  [1 << 14, 0, 0, 0, 1 << 6],
                                  [1 << 13, 0, 0, 0, 1 << 7],
                                  [1 << 12, 1 << 11, 1 << 10, 1 << 9, 1 << 8]])

    def lbp_image_his_8_1ij(self):
        lbp_image = np.zeros((self.image.shape[0] - 2, self.image.shape[1] - 2), dtype=np.uint8)
        for i in range(2, self.image.shape[0] - 2):
            for j in range(2, self.image.shape[1] - 2):
                temp = self.image[i - 1:i + 2, j - 1:j + 2]
                lbp_image[i - 2, j - 2] = sum(sum(np.array(temp < temp[1, 1], dtype=np.int8) * self.mask8_1))
        return np.unique(lbp_image, return_counts=True)[1]

    def lbp_image_his_8_1ji(self):
        lbp_image = np.zeros((self.image.shape[0] - 2, self.image.shape[1] - 2), dtype=np.uint8)
        for j in range(2, self.image.shape[1] - 2):
            for i in range(2, self.image.shape[0] - 2):
                temp = self.image[i - 1:i + 2, j - 1:j + 2]
                lbp_image[i - 2, j - 2] = sum(sum(np.array(temp < temp[1, 1], dtype=np.int8) * self.mask8_1))
        return np.unique(lbp_image, return_counts=True)[1]

    def lbp_image_his_8_2ij(self):
        lbp_image = np.zeros((self.image.shape[0] - 2, self.image.shape[1] - 2), dtype=np.uint8)
        for i in range(2, self.image.shape[0] - 2):
            for j in range(2, self.image.shape[1] - 2):
                temp = self.image[i - 2:i + 3, j - 2:j + 3]
                lbp_image[i - 2, j - 2] = sum(sum(np.array(temp < temp[1, 1], dtype=np.int8) * self.mask8_2))
        return np.unique(lbp_image, return_counts=True)[1]

    def lbp_image_his_8_2ji(self):
        lbp_image = np.zeros((self.image.shape[0] - 1, self.image.shape[1] - 1), dtype=np.uint8)
        for j in range(2, self.image.shape[1] - 2):
            for i in range(2, self.image.shape[0] - 2):
                temp = self.image[i - 2:i + 3, j - 2:j + 3]
                lbp_image[i - 2, j - 2] = sum(sum(np.array(temp < temp[2, 2], dtype=np.int8) * self.mask8_2))
        return np.unique(lbp_image, return_counts=True)[1]

    def lbp_image_his_16_2ij(self):
        lbp_image = np.zeros((self.image.shape[0] - 2, self.image.shape[1] - 2), dtype=np.uint8)
        for i in range(2, self.image.shape[0] - 2):
            for j in range(2, self.image.shape[1] - 2):
                temp = self.image[i - 2:i + 3, j - 2:j + 3]
                lbp_image[i - 2, j - 2] = sum(sum(np.array(temp < temp[1, 1], dtype=np.int8) * self.mask16_2))
        return np.unique(lbp_image, return_counts=True)[1]

    def lbp_image_his_16_2ji(self):
        lbp_image = np.zeros((self.image.shape[0] - 1, self.image.shape[1] - 1), dtype=np.uint16)
        for j in range(2, self.image.shape[1] - 2):
            for i in range(2, self.image.shape[0] - 2):
                temp = self.image[i - 2:i + 3, j - 2:j + 3]
                lbp_image[i - 2, j - 2] = sum(sum(np.array(temp < temp[2, 2], dtype=np.int16) * self.mask16_2))
        return np.unique(lbp_image, return_counts=True)[1]


print('Staring')
for repetition in range(105):
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
