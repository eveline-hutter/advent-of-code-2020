package day25;

public class R1 {

    public static long getEncryptionKey() {
        int publicKeyCard = 3469259;
        int publicKeyDoor = 13170438;
        int loopSizeCard = getLoopSize(publicKeyCard);
        int loopSizeDoor = getLoopSize(publicKeyDoor);
        // calculate encryption key (combine loop size card with public key door or vice versa)
        long encryptionKey = 1;
        for (int i = 0; i < loopSizeDoor; i++) {
            encryptionKey = (encryptionKey * publicKeyCard) % 20201227;
        }
        return encryptionKey;
    }

    public static int getLoopSize(int publicKey) {
        int loopSize = 0;
        int value = 1;
        int subjectNo = 7;
        while (value != publicKey) {
            loopSize++;
            value = (value * subjectNo) % 20201227;
        }
        return loopSize;
    }
    
    public static void main(String[] args) {
        System.out.println(getEncryptionKey());
    }

}
