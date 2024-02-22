# App Lista de Pets

Aplicativo desenvolvido na plataforma `Android`. Como já atuo no mercado de trabalho a mais de 10 anos, eu me sinto mais confortável em desenvolver nesta plataforma.

- Lista de gatos e cães utilizando a Apis:
- [Dogs](https://www.thedogapi.com/)

## Passo - 1

Adicionar API_KEY dentro do arquivo local.properties para poder realizar o consumo da Api de dogs citada acima

```local.properties
    API_KEY="live_YhXlXFoss12Zqbc7DekcGryCNRsmwbpPCmq4MjKytrwqhv0JmY3Y4TbAtR30UWq9"
```

## Passo - 2

Executar o projeto no Android Studio na `Build variante de DevelopmentDebug`

## Passo - 3

Dentro da pasta `app/assets/user.json` utilizar um e-mail a sua escolha para acessar o App.

## Passo - 4

Ao Acessar o aplicativo vai exibir duas categorias, uma para `Cats` e outra para `Dogs` ao clicar em uma das
opções o usuário vai para tela do seu pet selecionado.

__Referências__:

- [Paginação](https://developer.android.com/jetpack/androidx/releases/paging?hl=pt-br) - Facilita no carregamento gradual dos dados.
- [Loading Images](https://developer.android.com/jetpack/compose/graphics/images/loading?hl=pt-br) - Utlizada para carregar imagens da Api.
- [Jetpack Compose](developer.android.com/jetpack/compose) - Toda aplicação foi construida utlizando a ferramente moderna para criar Uis nativas.
- [Hilt](https://developer.android.com/training/dependency-injection/hilt-android?h) - O Hilt é uma biblioteca de injeção de dependências para Android que reduz o código boilerplate criado por injeções manuais no projeto.
- [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel?hl=pt-br) - O framework do Android gerencia o ciclo de vida dos controladores de IU, como atividades e fragmentos.
- [Retrofit](https://square.github.io/retrofit/) -  Bibliotecas de código aberto criadas pela comunidade para criar uma camada de dados e receber dados de um servidor de back-end.
- [Lottiefiles](https://lottiefiles.com) - LottieFiles elimina a complexidade do design de movimento.
