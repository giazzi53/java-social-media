# java-social-media

Acesso:

API: /signUp
Descrição: fazer o cadastro de um novo profissional
Método HTTP: POST
JSON:
{
    "name": "John Doe 8",
    "userLogin": "jdoe8",
    "email":"email@hotmail.com",
    "password": "pass123456",
    "profileImage": !passar algum base64 em forma de String!,
    "birthDate": "1",
    "careerDate": "1",
    "city": "SP",
    "state": "São Paulo",
    "jobRole": {
    	"jobTitle":"Cientista de dados",
        "salary": 1000,
        "companyName": "IBM"
    },
    "instructionLevel": "MESTRE",
    "profileType": "PREMIUM",
    "paymentInfo":{
    	"cardNumber":"1234 5689 6532 0092",
    	"cardName": "John Doe 2",
    	"cardValidationDate":"1",
    	"cardSecurityCode":"222"
    }
}

API: /login
Descrição: fazer o login de um profissional existente
Método HTTP: GET
JSON:
{
    "userLogin": "uniquelogin",
    "password": "aaaa"
}

API: /updateProfile
Descrição: atualizar os dados do perfil de um profissional existente
Método HTTP: PUT
JSON (passar os campos que quiser atalizar):
{
    "professionalID": "id do profissional",
    "name": "Marquinhos 2"
}

API : /retrieveProfessionalData
Descrição: retorna todos os dados do usuário
Método HTTP: POST
JSON
{
    "professionalID": "id do profissional"
}

-------------------------------

Publicação:

API: /publicate
Descrição: criar uma nova publicação
Método HTTP: POST
JSON (ainda vai ter a data):
{
    "professionalID": "id do profissional",
    "text": "5 Marquinhos says hi"
}

API: /retrievePublicationList
Descrição: obter todas as publicações de um profissional existente
Método HTTP: GET
URL: https://java-ds-social-media.herokuapp.com/retrievePublicationList/{professionalID}

API: /retrieveFeedPublicationsList
Descrição: obter todas as publicações de um profissional existente
Método HTTP: GET
URL: https://java-ds-social-media.herokuapp.com/retrieveFeedPublicationsList/{professionalID}

API: /deletePublication
Descrição: excluir uma publicação existente
Método HTTP: DELETE
JSON:
{
    "publicationID": "id da publicação"
}

-------------------------------

Friendship:

API: /sendFriendshipRequest
Descrição: Envia uma requisição de amizade para um profissional
Método HTTP: POST
JSON:
[
    {
        "professionalID": "5d717b0bb13b8117f4067648" (id do usuário logado)
    },
    {
        "professionalID": "5d717ac2b13b8117f4067647" 
    }
]

API: /acceptFriendshipRequest
Descrição: Aceita uma requisição de amizade de um profissional
Método HTTP: POST
JSON:
[
    {
        "professionalID": "5d717ac2b13b8117f4067647" (id do usuario logado)
    },
    {
        "professionalID": "5d717b0bb13b8117f4067648"
    }
]

API: /rejectFriendshipRequest
Descrição: Rejeita uma requisição de amizade de um profissional
Método HTTP: POST
JSON:
[
    {
        "professionalID": "5d717ac2b13b8117f4067647" (id do usuario logado)
    },
    {
        "professionalID": "5d717b0bb13b8117f4067648"
    }
]

API: /returnListFriends
Descrição: Retorna uma lista de objetos Professional, com todos os amigos do usuário
Método HTTP: GET
URL: https://java-ds-social-media.herokuapp.com/returnListFriends/{professionalID}

API: /getFriendsInCommon/{professionalID1}/{professionalID2}
Descrição: retorna as amizades em comum entre dois profissionais
Método HTTP: GET

-------------------------------

Search:

API: /search
Descrição: busca usuários pelo nome
Método HTTP: POST
JSON:
{
    "name": "nome a ser buscado",
}
----------------------------------

InterestTopic:

API: /getInterestTopics
Descrição: retorna todos os topicos de interesse cadastrados no banco
Método HTTP: GET
URL: https://java-ds-social-media.herokuapp.com/getInterestTopics

----------------------------------

Professional_InterestTopic

API: /updateProfessionalInterestTopics
Descrição: faz o update nos topicos de interesse do professional
Método HTTP: POST
JSON:
[
    {
        "professionalID": "id do professional",
        "interestTopicID": "id do topico"
    },
    {
        "professionalID": "id do professional",
        "interestTopicID": "id do topico"
    },
    {
        "professionalID": "id do professional",
        "interestTopicID": "id do topico"
    }
]

API: /getProfessionalInterestTopics
Descrição: retorna os topicos de interesse do usuario
Método HTTP: GET
URL : https://java-ds-social-media.herokuapp.com/getProfessionalInterestTopics/{professionalID}


