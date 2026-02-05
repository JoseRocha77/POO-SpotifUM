# 🎵 SpotifUM - Sistema de Gestão de Streaming Musical

Este projeto foi desenvolvido para a Unidade Curricular de **Programação Orientada a Objetos (POO)** da Licenciatura em Engenharia Informática na Universidade do Minho (2024/2025). O objetivo é a implementação de uma plataforma de streaming de música robusta, utilizando os pilares da POO em **Java**.

## 👥 Elementos do Grupo (Grupo 25)
* Diogo Alves - A106904
* Hugo Cunha - A106808
* José Rocha - A106887

---

## 📝 Descrição do Projeto
O **SpotifUM** é uma aplicação que permite gerir um vasto catálogo de músicas, álbuns e playlists, oferecendo diferentes experiências de utilização consoante o plano de subscrição do utilizador.

### Principais Conceitos Aplicados:
* **Encapsulamento Total:** Proteção dos dados internos das classes.
* **Herança e Polimorfismo:** Hierarquias de classes para Músicas (Explícitas, Multimédia) e Playlists.
* **Abstração:** Utilização de Interfaces para definir comportamentos (ex: Planos de Subscrição).
* **Persistência de Dados:** Salvaguarda do estado da aplicação em ficheiros de objetos.

---

## 🚀 Funcionalidades

* **Gestão de Conteúdos:** Criação e organização de Músicas, Álbuns e Artistas.
* **Playlists Inteligentes:**
    * Playlists Aleatórias.
    * Playlists Construídas (seleção manual).
    * Playlists de Favoritos e por Género Musical.
* **Sistema de Utilizadores:**
    * **Plano Free:** Reprodução limitada e aleatória.
    * **Plano Premium (Base/Top):** Controlo total sobre a reprodução e funcionalidades avançadas.
* **Estatísticas:** Monitorização do estado do programa e hábitos de reprodução.
* **Persistência:** Gravação e carregamento do estado completo (ficheiros `.dat` / `.obj`).

---

## 🏗️ Arquitetura do Sistema

A aplicação está dividida em módulos lógicos seguindo o padrão **MVC** (Model-View-Controller):
* **Model:** Classes de dados (Musica, Utilizador, Album, etc.).
* **View:** Interface de interação com o utilizador (Consola).
* **Controller:** Lógica de negócio e gestão de eventos.

---

## 🛠️ Tecnologias Utilizadas
* **Linguagem:** Java 17+
* **Paradigma:** Orientação a Objetos
* **Ferramentas:** Java Collections Framework, Streams API, Serialização.

---

## ⚙️ Como Compilar e Executar

### Pré-requisitos
* Java Development Kit (JDK) instalado.

### Compilação e Execução
1. Clona o repositório:
   ```bash
   git clone https://github.com/JoseRocha77/POO-SpotifUM.git
   ```
2. Compila os ficheiros .java:
   ```bash
   javac -d bin src/*.java
   ```
3. Executa a aplicação
   ```bash
   java -cp bin Main
   ```

---
## 📂 Estrutura de Pastas

* /src: Código-fonte da aplicação (.java).
* /doc: Relatório técnico e Diagrama de Classes (UML).

