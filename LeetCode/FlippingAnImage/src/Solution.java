
public class Solution {
    public int[][] flipAndInvertImage(int[][] image) {
        for (int[] im : image) {
            int length = im.length;
            for (int i = 0; i < (length % 2 == 0 ? length / 2 : length / 2 + 1); i++) {
                int temp = im[i];
                im[i] = (im[im.length - 1 - i] + 1) % 2;
                im[im.length - 1 - i] = (temp + 1) % 2;
            }
        }
        return image;
    }
}
