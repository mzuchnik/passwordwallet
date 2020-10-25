# passwordwallet

Password wallet is a web application created using Spring boot. It allows the user to register and choose how the password will be encrypted (HMAC, BCrypt). After logging in, the user has the opportunity to add information about his pages to the login. Information such as: page address, login, password and description. The password for each portal is encrypted using AES where the encryption key is the user's main password. After changing the password to the user's account, the encryption programs to the password wallet also change. So that decryption is possible.
