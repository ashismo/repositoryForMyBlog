# Create CSR file
Reference URL: https://www.godaddy.com/help/generate-a-csr-certificate-signing-request-5343  
Login to SSH and execute the following command  
  - openssl req -new -newkey rsa:2048 -nodes -keyout yourdomain.key -out yourdomain.csr  
  - Enter the required details on the prompt

This would generate yourdomain.key and yourdomain.csr files

Update the following details in the httpd-ssl.conf file  
vi /opt/lampp/etc/extra/httpd-ssl.conf  

SSLCertificateFile "/opt/lampp/OpenSSL/certs/<>.crt"  
SSLCertificateKeyFile "/opt/lampp/OpenSSL/certs/<>.key"

