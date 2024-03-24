# 스프링 시큐리티 인 액션

## https 인증서 만들기

``` zsh
# key file 생성
$ openssl req -newkey rsa:2048 -x509 -keyout key.pem -out cert.pem -days 365

# key 옵션
Password: 1234
Country Name (2 letter code) []:sa
State or Province Name (full name) []:kkkkkksssssaaaa-dev
Locality Name (eg, city) []:city
Organization Name (eg, company) []:company    
Organizational Unit Name (eg, section) []:section
Common Name (eg, fully qualified host name) []:
Email Address []:

# key file 을 통한 자체 서명 인증서 생성
$  openssl pkcs12 -export -in cert.pem -inkey key.pem -out certificate.p12 -name "certificate"

Enter pass phrase for key.pem: 1234
Enter Export Password: 1234
Verifying - Enter Export Password: 1234
```