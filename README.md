# Cypher - Encryption & Decryption
Give the ability to encrypt and decrypt text. Encryption and decryption keys will be stored in the "secret.key" file

#### Features
1) Encrypt text
2) Decrypt text

#### Methods
1) >.encrypt(plainText);
2) >.decrypt(encryptedText);

#### How to Use
1) Download the .jar file and add it to your project
2) Import Library:
   
   ```java
   
   import com.Cypher;
   
   ```

3) Use Methods: </br></br>
    i) .encrypt(plainText) </br>
    > This method is used to encrypt the given text
    
   ```java
   
   try {
       String encrypt = Cypher.encrypt(plainText);
   } catch (Exception e) {
       e.printStackTrace();
   }
   
   ```
    ii) .decrypt(encryptedText) </br>
    > This method is used to decrypt the encrypted text
    
   ```java
   
   try {
       String decrypt = Cypher.decrypt(encryptedText);
   } catch (Exception e) {
       e.printStackTrace();
   }
   
   ```

  #### Outputs:
  1) .encrypt(plainText) -> encrypted text
  2) .decrypt(encryptedText) -> decrypted text

#### Example Code:

```java

import com.Cypher;

public class B {

    public static void main(String[] args) {
        try {
            
            String decrypt = Cypher.decrypt("");
            String encrypt = Cypher.encrypt("");
            
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}


```
 

