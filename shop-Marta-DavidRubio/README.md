
CAMBIOS REALIZADOS: 


8/05/25 ERROR 403
    En  ApiSecurityConfig, he cambiado los path, añadiendo /home/page
8/05/25 error 500
En unos busco por username y en otros por email

petidos preparadas para la corrección:
"dev": {
"tokenUser1": "eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoiQUNDRVNTIiwic3ViIjoiam9zZWx1aXNAZXhhbXBsZS5jb20iLCJmaXJzdE5hbWUiOiJKb3NlIGx1aXMiLCJsYXN0TmFtZSI6ImxvcGV6IiwiaWF0IjoxNzQ3NjEyNDg0LCJleHAiOjE3NDc2MTMzODR9.VLBvaQ_7c-8wuYU87PdqCQWlZ6RnPMff6njXXrYx6cc",
"tokenUser2": "eyJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoiQUNDRVNTIiwic3ViIjoiZGF2aWRAZXhhbXBsZS5jb20iLCJmaXJzdE5hbWUiOiJEYXZpZCIsImxhc3ROYW1lIjoiUnViaW8iLCJpYXQiOjE3NDc2MTI1MDAsImV4cCI6MTc0NzYxMzQwMH0.abGjp6iMbr4JFkybupisfP89jluVJL90zw2o6nVVg8o",
"productIdUser1": "6",
"productIdUser2": "12",
"quantityUser1": "3",
"quantityUser2": "15"
}

###
POST http://localhost:8000/home/app/api/app/v1/auth/register
Content-Type: application/json

{
"firstName": "sara",
"lastName": "risco",
"email": "sara@example.com",
"password": "sara"
}


###
POST http://localhost:8000/home/app/api/app/v1/auth/login
Content-Type: application/json

{
"email": "joseluis@example.com",
"password": "joseluis"
}



###
POST http://localhost:8000/home/app/api/app/v1/auth/login
Content-Type: application/json

{
"email": "david@example.com",
"password": "david"
}



###
GET http://localhost:8000/home/app/api/app/v1/products/find
?search=magic
Accept: application/json
Authorization: Bearer {{tokenUser1}}

###
GET http://localhost:8000/home/app/api/app/v1/products/find
?search=Counterspell
Accept: application/json
Authorization: Bearer {{tokenUser1}}

###
GET http://localhost:8000/home/app/api/app/v1/products/find?
cat=1&
sortBy=description&
sortDir=desc&
pageNumber=1&
pageSize=5
Accept: application/json
Authorization: Bearer {{tokenUser1}}


###
POST http://localhost:8000/home/app/api/app/v1/cart/{{productIdUser1}}
Content-Type: application/x-www-form-urlencoded
Authorization: Bearer {{tokenUser1}}



###
POST http://localhost:8000/home/app/api/app/v1/cart/{{productIdUser2}}
Content-Type: application/x-www-form-urlencoded
Authorization: Bearer {{tokenUser2}}



###
POST http://localhost:8000/home/app/api/app/v1/cart/{{productIdUser1}}/{{quantityUser1}}
Content-Type: application/x-www-form-urlencoded
Authorization: Bearer {{tokenUser1}}



###
POST http://localhost:8000/home/app/api/app/v1/cart/{{productIdUser2}}/{{quantityUser2}}
Content-Type: application/x-www-form-urlencoded
Authorization: Bearer {{tokenUser2}}


###
DELETE http://localhost:8000/home/app/api/app/v1/cart/{{productIdUser1}}
Authorization: Bearer {{tokenUser1}}



###
DELETE http://localhost:8000/home/app/api/app/v1/cart/{{productIdUser2}}
Authorization: Bearer {{tokenUser2}}



###
DELETE http://localhost:8000/home/app/api/app/v1/cart
Authorization: Bearer {{tokenUser1}}

###
DELETE http://localhost:8000/home/app/api/app/v1/cart
Authorization: Bearer {{tokenUser2}}

###
GET http://localhost:8000/home/app/api/app/v1/categories
Accept: application/json
Authorization: Bearer {{tokenUser1}}

###
GET http://localhost:8000/home/app/api/app/v1/products/find?
search={{$random.alphanumeric(8)}}&
cat={{$random.integer(100)}}&
sortBy={{$random.alphanumeric(8)}}&
sortDir={{$random.alphanumeric(8)}}&
pageNumber={{$random.integer(100)}}&
pageSize={{$random.integer(100)}}




