import numpy as np
from skimage import io
import time


class LBP:

    def __init__(self, image_route):
        self.image_route = image_route
        self.image = np.array(io.imread(image_route, as_gray=True) * 255, dtype=np.uint8)
        self.lbp_image = np.zeros((self.image.shape[0] - 2, self.image.shape[1] - 2), dtype=np.uint16)

    def lbp_image_his_8_1ij(self):
        for i in range(2, self.image.shape[0] - 2):
            for j in range(2, self.image.shape[1] - 2):
                mask: np.uint8 = np.uint8(0)
                pixel: np.uint8 = self.image[i, j]
                mask |= (self.image[i - 1, j - 1] < pixel) << 7
                mask |= (self.image[i, j - 1] < pixel) << 6
                mask |= (self.image[i + 1, j - 1] < pixel) << 5
                mask |= (self.image[i + 1, j] < pixel) << 4
                mask |= (self.image[i + 1, j + 1] < pixel) << 3
                mask |= (self.image[i, j + 1] < pixel) << 2
                mask |= (self.image[i - 1, j + 1] < pixel) << 1
                mask |= (self.image[i - 1, j] < pixel) << 0
                self.lbp_image[i - 2, j - 2] = mask
        return self.lbp_image

    def lbp_image_his_8_1ji(self):
        for j in range(2, self.image.shape[1] - 2):
            for i in range(2, self.image.shape[0] - 2):
                mask: np.uint8 = np.uint8(0)
                pixel: np.uint8 = self.image[i, j]
                mask |= (self.image[i - 1, j - 1] < pixel) << 7
                mask |= (self.image[i, j - 1] < pixel) << 6
                mask |= (self.image[i + 1, j - 1] < pixel) << 5
                mask |= (self.image[i + 1, j] < pixel) << 4
                mask |= (self.image[i + 1, j + 1] < pixel) << 3
                mask |= (self.image[i, j + 1] < pixel) << 2
                mask |= (self.image[i - 1, j + 1] < pixel) << 1
                mask |= (self.image[i - 1, j] < pixel) << 0
                self.lbp_image[i - 2, j - 2] = mask
        return self.lbp_image

    def lbp_image_his_8_2ij(self):
        for i in range(2, self.image.shape[0] - 2):
            for j in range(2, self.image.shape[1] - 2):
                mask: np.uint8 = np.uint8(0)
                pixel: np.uint8 = self.image[i, j]
                mask |= (self.image[i - 2, j - 2] < pixel) << 7
                mask |= (self.image[i, j - 2] < pixel) << 6
                mask |= (self.image[i + 2, j - 2] < pixel) << 5
                mask |= (self.image[i + 2, j] < pixel) << 4
                mask |= (self.image[i + 2, j + 2] < pixel) << 3
                mask |= (self.image[i, j + 2] < pixel) << 2
                mask |= (self.image[i - 2, j + 2] < pixel) << 1
                mask |= (self.image[i - 2, j] < pixel) << 0
                self.lbp_image[i - 2, j - 2] = mask
        return self.lbp_image

    def lbp_image_his_8_2ji(self):
        for j in range(2, self.image.shape[1] - 2):
            for i in range(2, self.image.shape[0] - 2):
                mask: np.uint8 = np.uint8(0)
                pixel: np.uint8 = self.image[i, j]
                mask |= (self.image[i - 2, j - 2] < pixel) << 7
                mask |= (self.image[i, j - 2] < pixel) << 6
                mask |= (self.image[i + 2, j - 2] < pixel) << 5
                mask |= (self.image[i + 2, j] < pixel) << 4
                mask |= (self.image[i + 2, j + 2] < pixel) << 3
                mask |= (self.image[i, j + 2] < pixel) << 2
                mask |= (self.image[i - 2, j + 2] < pixel) << 1
                mask |= (self.image[i - 2, j] < pixel) << 0
                self.lbp_image[i - 2, j - 2] = mask
        return self.lbp_image

    def lbp_image_his_16_2ij(self):
        for i in range(2, self.image.shape[0] - 2):
            for j in range(2, self.image.shape[1] - 2):
                mask: np.uint16 = np.uint16(0)
                pixel: np.uint8 = self.image[i, j]
                mask |= (self.image[i - 2, j - 1] < pixel) << 15
                mask |= (self.image[i - 2, j - 2] < pixel) << 14
                mask |= (self.image[i - 1, j - 2] < pixel) << 13
                mask |= (self.image[i, j - 2] < pixel) << 12
                mask |= (self.image[i + 1, j - 2] < pixel) << 11
                mask |= (self.image[i + 2, j - 2] < pixel) << 10
                mask |= (self.image[i + 2, j - 1] < pixel) << 9
                mask |= (self.image[i + 2, j] < pixel) << 8
                mask |= (self.image[i + 2, j + 1] < pixel) << 7
                mask |= (self.image[i + 2, j + 2] < pixel) << 6
                mask |= (self.image[i + 1, j + 2] < pixel) << 5
                mask |= (self.image[i, j + 2] < pixel) << 4
                mask |= (self.image[i - 1, j + 2] < pixel) << 3
                mask |= (self.image[i - 2, j + 2] < pixel) << 2
                mask |= (self.image[i - 2, j + 1] < pixel) << 1
                mask |= (self.image[i - 2, j] < pixel) << 0
                self.lbp_image[i - 2, j - 2] = mask
        return self.lbp_image

    def lbp_image_his_16_2ji(self):
        for j in range(2, self.image.shape[1] - 2):
            for i in range(2, self.image.shape[0] - 2):
                mask: np.uint16 = np.uint16(0)
                pixel: np.uint8 = self.image[i, j]
                mask |= (self.image[i - 2, j - 1] < pixel) << 15
                mask |= (self.image[i - 2, j - 2] < pixel) << 14
                mask |= (self.image[i - 1, j - 2] < pixel) << 13
                mask |= (self.image[i, j - 2] < pixel) << 12
                mask |= (self.image[i + 1, j - 2] < pixel) << 11
                mask |= (self.image[i + 2, j - 2] < pixel) << 10
                mask |= (self.image[i + 2, j - 1] < pixel) << 9
                mask |= (self.image[i + 2, j] < pixel) << 8
                mask |= (self.image[i + 2, j + 1] < pixel) << 7
                mask |= (self.image[i + 2, j + 2] < pixel) << 6
                mask |= (self.image[i + 1, j + 2] < pixel) << 5
                mask |= (self.image[i, j + 2] < pixel) << 4
                mask |= (self.image[i - 1, j + 2] < pixel) << 3
                mask |= (self.image[i - 2, j + 2] < pixel) << 2
                mask |= (self.image[i - 2, j + 1] < pixel) << 1
                mask |= (self.image[i - 2, j] < pixel) << 0
                self.lbp_image[i - 2, j - 2] = mask
        return self.lbp_image

    @staticmethod
    def make_global_histogram(lbp_image: np.ndarray):
        return np.unique(lbp_image, return_counts=True)[1]
