import java.math.BigInteger;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

public class DSA {
    public static void main(String[] args) {
        if (args.length < 3 || (args[2].equals("v") && args.length < 4)) {
            System.out.println(
                    "Usage: java -cp out DSA <path-to-document> <number-of-signatures> <'s' or 'v'> [<path-to-signature>]");
            return;
        }
        String documentPath = args[0];
        int numSignatures;
        try {
            numSignatures = Integer.parseInt(args[1]);
            if (numSignatures < 1) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number of signatures.");
            System.out.println(
                    "Usage: java -cp out DSA <path-to-document> <number-of-signatures> <'s' or 'v'> [<path-to-signature>]");
            return;
        }
        char mode = args[2].charAt(0);
        if (mode != 's' && mode != 'v') {
            System.out.println(
                    "Usage: java -cp out DSA <path-to-document> <number-of-signatures> <'s' or 'v'> [<path-to-signature>]");
            return;
        }

        String documentContent = Utils.readFile(documentPath);
        if (documentContent == null) {
            System.out.println("Error reading file.");
            return;
        }

        KeyPair keyPair = DSAGenerator.generateDSAKeyPair();
        if (mode == 's') {
            long startTime = System.currentTimeMillis();
            BigInteger[] signature = DSASigner.signData(documentContent, keyPair.getPrivate());

            for (int i = 1; i < numSignatures; i++) {
                DSASigner.signData(documentContent, keyPair.getPrivate());
            }

            long endTime = System.currentTimeMillis();
            long elapsedTime = endTime - startTime;

            System.out.println("Document: " + documentContent);
            System.out.println("First signature: r=" + signature[0] + ", s=" + signature[1]);
            System.out.println("Total time for " + numSignatures + " signatures: " + elapsedTime + " ms");
            DSAPublicKey publicKey = keyPair.getPublic();
            System.out.println("PublicKey for signature: " + publicKey.getY());
            String filename = generateUniqueFilename("./tests/sign", ".txt");
            saveSignatureToFile(filename, keyPair.getPublic(), signature);
        } else {
            String signaturePath = args[3];
            String signatureContent = Utils.readFile(signaturePath);
            if (signatureContent == null) {
                System.out.println("Error reading signature file.");
                return;
            }

            String[] parts = signatureContent.split(",");
            if (parts.length != 3) {
                System.out.println("Invalid signature format. Format (k,r,s) expected");
                return;
            }

            BigInteger k = new BigInteger(parts[0]);
            BigInteger r = new BigInteger(parts[1]);
            BigInteger s = new BigInteger(parts[2]);
            BigInteger[] signature = { r, s };

            DSAPublicKey publicKeyforVerif = new DSAPublicKey(k);

            long startTime = System.currentTimeMillis();

            boolean isValid = DSAVerifier.verifySignature(documentContent, signature, publicKeyforVerif);
            for (int i = 1; i < numSignatures; i++) {
                DSAVerifier.verifySignature(documentContent, signature, publicKeyforVerif);
            }

            long endTime = System.currentTimeMillis();
            long elapsedTime = endTime - startTime;

            System.out.println("Signature valid: " + isValid);
            System.out.println("Total time for " + numSignatures + " verification: " + elapsedTime + " ms");
        }
    }

    private static String generateUniqueFilename(String baseName, String extension) {
        int counter = 1;
        String filename = baseName + counter + extension;
        File file = new File(filename);

        while (file.exists()) {
            counter++;
            filename = baseName + counter + extension;
            file = new File(filename);
        }

        return filename;
    }

    private static void saveSignatureToFile(String filename, DSAPublicKey publicKey, BigInteger[] signature) {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write(publicKey.getY() + "," + signature[0] + "," + signature[1]);
            System.out.println("Signature saved to " + filename);
        } catch (IOException e) {
            System.out.println("Error saving signature to file " + filename);
            e.printStackTrace();
        }
    }
}