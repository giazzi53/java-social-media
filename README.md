# java-social-media

Acesso:

API: /signUp
Descrição: fazer o cadastro de um novo profissional
Método HTTP: POST
JSON (ainda vão ter os outros campos):
{
    "name": "My Name",
    "userLogin": "uniquelogin",
    "password": "aaaa"
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
JSON:
{
    "professionalID": "id do profissional"
}

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
Resposta da API:
{
    "friendshipRequestId": "5d73394b132bf23ccc61b4e3",
    "professionalID1": "5d717b0bb13b8117f4067648",
    "professionalID2": "5d717ac2b13b8117f4067647"
}

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
Resposta da API:
{
    "friendshipId": "5d73371d132bf23ccc61b4da",
    "professionalID1": "5d717b0bb13b8117f4067648",
    "professionalID2": "5d717ac2b13b8117f4067647"
}

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
Resposta da API:
{
    "friendshipRequestId": "5d73394b132bf23ccc61b4e3",
    "professionalID1": "5d717b0bb13b8117f4067648",
    "professionalID2": "5d717ac2b13b8117f4067647"
}

API: /returnListFriends
Descrição: Retorna uma lista de objetos Professional, com todos os amigos do usuário
Método HTTP: POST
JSON:
{
    "professionalID": "5d717ac2b13b8117f4067647"
}
Resposta da API:
[
    {
        "professionalID": "5d7089eb132bf2447055c80b",
        "name": "1",
        "userLogin": "1",
        "password": null,
        "profileImage": null,
        "birthDate": null,
        "careerDate": null,
        "jobRole": null,
        "instructionLevel": null,
        "profileType": null,
        "jobRoleID": null
    },
    {
        "professionalID": "5d717b0bb13b8117f4067648",
        "name": "Renan Biaigotti",
        "userLogin": "renansao",
        "password": null,
        "profileImage": null,
        "birthDate": null,
        "careerDate": null,
        "jobRole": null,
        "instructionLevel": null,
        "profileType": null,
        "jobRoleID": null
    }
]