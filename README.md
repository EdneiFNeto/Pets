# App Lista de Pets

Aplicativo desenvolvido na plataforma `Android`, a funcionalidade de testar o consumo de Apis de [cães](https://www.thedogapi.com/), utilizando a arquitetura MVVM com injeção de dependência com Hilt & animações com looties.

## Passo - 1

Adicionar API_KEY dentro do arquivo local.properties para poder realizar o consumo da Api de dogs citada acima

```local.properties
API_KEY="live_YhXlXFoss12Zqbc7DekcGryCNRsmwbpPCmq4MjKytrwqhv0JmY3Y4TbAtR30UWq9"
```

## Passo - 2

Executar o projeto no Android Studio na `Build variante de DevelopmentDebug`

## Passo - 3

Escolher um e-mail a sua escolha para acessar o App.

```JSON
[
  {
    "name": "Jose",
    "email": "jose@gmail.com"
  },
  {
    "name": "ana@gmail.com",
    "email": "ana@gmail.com"
  },
  {
    "name": "Mamaria@gmail.com",
    "email": "mamaria@gmail.com"
  },
  {
    "name": "Claudia",
    "email": "claudia@gmail.com"
  },
  {
    "name": "Joana",
    "email": "joana@gmail.com"
  },
  {
    "name": "Marta",
    "email": "marta@gmail.com"
  }
]
```

## Passo - 4

Ao Acessar o aplicativo vai exibir duas categorias, uma para `Cats` e outra para `Dogs` ao clicar em uma das
opções o usuário vai para tela do seu pet selecionado.

## Screenshots

<p>
    <img src="/screenshots/login.png" width="300" height="600">
    <img src="/screenshots/home.png" width="300" height="600">
    <img src="/screenshots/list-dogs.png" width="300" height="600">
    <img src="/screenshots/detail-dog.png" width="300" height="600">
<p>

__Referências__:

- [Loading Images](https://developer.android.com/jetpack/compose/graphics/images/loading?hl=pt-br) - Utlizada para carregar imagens da Api.
- [Jetpack Compose](developer.android.com/jetpack/compose) - Toda aplicação foi construida utlizando a ferramente moderna para criar Uis nativas.
- [Hilt](https://developer.android.com/training/dependency-injection/hilt-android?h) - O Hilt é uma biblioteca de injeção de dependências para Android que reduz o código boilerplate criado por injeções manuais no projeto.
- [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel?hl=pt-br) - O framework do Android gerencia o ciclo de vida dos controladores de IU, como atividades e fragmentos.
- [Retrofit](https://square.github.io/retrofit/) -  Bibliotecas de código aberto criadas pela comunidade para criar uma camada de dados e receber dados de um servidor de back-end.
- [Lottiefiles](https://lottiefiles.com) - LottieFiles elimina a complexidade do design de movimento.
