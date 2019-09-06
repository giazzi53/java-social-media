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