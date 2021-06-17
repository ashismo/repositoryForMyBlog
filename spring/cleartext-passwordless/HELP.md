# How to create the password?
java -cp jasypt-1.9.3.jar org.jasypt.intf.cli.JasyptPBEStringEncryptionCLI input="Delta123" password=secretkey algorithm=PBEWithMD5AndDES

