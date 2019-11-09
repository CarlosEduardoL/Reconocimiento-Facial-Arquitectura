import numpy as np
from skimage import io
import matplotlib.pyplot as plt


class LBP:

    def __init__(self, image_route):
        self.image_route = image_route
        self.image = np.array(io.imread(image_route, as_gray=True) * 255, dtype=np.uint8)
        self.lbp_image = np.zeros(self.image.shape, dtype=np.uint8)
        self.__convert = 1 << np.array([i for i in range(7, -1, -1)], dtype=np.uint8)
        self.generate_lbp_image()

    def generate_lbp_image(self):
        for i in range(1, self.image.shape[0] - 1):
            for j in range(1, self.image.shape[1] - 1):
                temp = self.image[i - 1:i + 2, j - 1:j + 2]
                self.lbp_image[i, j] = self.__evaluate(temp[1, 1],
                                                       np.array(
                                                           [temp[0, 1], temp[0, 0], temp[1, 0], temp[2, 0], temp[2, 1],
                                                            temp[2, 2], temp[1, 2], temp[0, 2]]))

    def __evaluate(self, num, neighbors: np.ndarray) -> np.uint8:
        return np.array(neighbors < num, dtype=np.int8).dot(self.__convert)


lbp = LBP('../images/img1.jpg')
plt.figure(1)
plt.imshow(lbp.image, cmap=plt.get_cmap('gray'), vmin=0, vmax=255)
plt.figure(2)
plt.imshow(lbp.lbp_image, cmap=plt.get_cmap('gray'), vmin=0, vmax=255)
plt.show()
