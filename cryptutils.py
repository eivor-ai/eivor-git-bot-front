from Crypto.Cipher import AES
import base64
import os

key = os.environ.get("EIVOR_KEY")
key32 = "{: <32}".format(key).encode("utf-8")
cipher = AES.new(key32, AES.MODE_ECB)


def encodestr(message: str):
    to_encode = message.rjust(32)
    return base64.b64encode(cipher.encrypt(to_encode))


def decodestr(message: str):
    return cipher.decrypt(base64.b64decode(message))
