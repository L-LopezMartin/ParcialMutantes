<div>
  <h1>Índice</h1>
  <ul>
    <li><a href="#descripci%C3%B3n-del-proyecto">Descripción del proyecto</a></li>
    <li><a href="#instrucciones-de-uso">Instrucciones de uso</a></li>
    <li><a href="#sobre-el-algoritmo">Sobre el algoritmo</a></li>
    <li><a href="#tests-autom%C3%A1ticos">Tests automáticos</a></li>
    <li><a href="https://github.com/L-LopezMartin/ParcialMutantes/edit/main/README.md#documentaci%C3%B3n">Documentación</a></li>
  </ul>
</div>


<div>
  <hr>
  <h1>Descripción del proyecto</h1>
  <p>El proyecto es una instancia evaluatoria para la cátedra de Desarrollo de Software. Consite en una API REST en Java con SpringBoot, Hibernate y JPA, con una base de datos en H2 mantenida en memoria.</p>
  <p>La aplicación está dividida en las capas de controlador, servicio y repositorio y cuenta con tests automáticos que prueban más del 80% de la aplicación.</p>
  <p>La API cuenta con 4 endpoints, 2 de los cuales no estaban incluídos en el enunciado pero consideré importantes y útiles añadir. Los endpoint son los siguientes:</p>
  <ul>
    <li><b>"/personas/mutant/": </b> Recibe un array de String que representa al genoma de una persona. Si el genoma está repetido en la base de datos, devuelve la persona guardada. Si no lo está, verifica el genoma en busca de al menos 2 cadenas de 4 caracteres iguales para designar a la persona como mutante, o en caso de no encontrarse 2 o más cadenas iguales, designarla como humano.</li>
    <li><b>"/stats": </b>Busca en la base de datos a todas las personas guardadas y devuelve la cantidad de personas que son mutantes, la cantidad de humanos y el ratio de mutantes por humanos.</li>
    <li><b>"/personas/getOne/{id}: "</b> Busca en la base de datos una persona cuyo ID coincida con el ingresado a través de la URL y devuelve su información. No era requerido en el enunciado.</li>
    <li><b>"/personas/getAll: "</b> Busca en la base de datos a todas las personas y devuelve la información de cada una. No era requerido en el enunciado.</li>
  </ul>
</div>


<div>
  <hr>
  <h1>Instrucciones de uso</h1>
  <p>La API se encuentra deployeada en render, el link es el siguiente: <a href="https://parcialmutantes-1rnn.onrender.com">https://parcialmutantes-1rnn.onrender.com</a> </p>
  <p>Para hacer requests a la API, usar los endpoints listados arriba a través de alguna aplicación como Postman, o a través de Swagger UI con el siguiente link: <a href="https://parcialmutantes-1rnn.onrender.com/swagger-ui/index.html#/">https://parcialmutantes-1rnn.onrender.com/swagger-ui/index.html#/</a></p>
</div>


<div>
  <hr>
  <h1>Sobre el algoritmo</h1>
  <p>El genoma recibido es un array de Strings, que puede ser interpretado como una matriz de caracteres de dos dimensiones.</p>
  <p>La matriz es dividida como se representa a continuación:</p>
  <img src="https://github.com/user-attachments/assets/9674c520-3227-4499-bf7b-0d41c1163a8f">
  <p>Las celdas marcadas con una cruz representan los caracteres que van a ser verificados. Cada caracter es de uno de dos tipos a los que llamo de "Diagonal Larga" o de "Diagonal Corta". El análisis varía levement dependiendo del tipo de caracter.</p>
  <img src="https://github.com/user-attachments/assets/a03ab997-6fde-4c42-8f58-2cbeb03fbf41">
  <p>El caracter marcado en azul es el que está siendo verificado actualmente. Se chequean las casillas amarillas para ver si el caracter guardado en ella es el mismo que el caracter de la casilla azul. En caso de que sea verdadero, se chequean las otras casillas necesarias, pero si no se da la situación se ahorra la verificación de un caracter.</p>
  <h2>Verificación horizontal</h2>
  <p>La verificación horizontal es igual para todo tipo de caracter.</p>
  <p>Para el caracter marcado en azul, se verifica la casilla marcada en verde oscuro. Si ambos son del mismo caracter, se chequean las casillas verde claro</p>
  <img src="https://github.com/user-attachments/assets/80f8fc4d-9c63-4008-af5b-4702af49ff37">
  <h2>Verificación vertical</h2>
  <h3>Diagonales cortas</h3>
  <img src="https://github.com/user-attachments/assets/1ef268f0-757f-4bf1-8b2f-557fd21e474d">
  <h3>Diagonales largas</h3>
  <p>Para diagonales largas, se pueden presentar 3 situaciones. La presencia de una de ellas impide que se pueda contabiizar otra de ellas como una cadena de 4 iguales</p>
  <img src="https://github.com/user-attachments/assets/9e1b326f-0428-46f6-aeb6-a2f29f6c3f28">
  <h2>Verificación diagonal hacia atrás</h2>
  <h3>Diagonales cortas</h3>
  <img src="https://github.com/user-attachments/assets/32cbf9dd-0407-4ff5-acb0-4b1c6af083a8">
  <h3>Diagonales largas</h3>
  <img src="https://github.com/user-attachments/assets/ef3c41e8-593b-47bb-aab5-b2bf0f45f32a">
  <h2>Verificación diagonal hacia delante</h2>
  <h3>Diagonales cortas</h3>
  <img src="https://github.com/user-attachments/assets/d8878207-95d8-47eb-97ff-99546f29c973">
  <h3>Diagonales largas</h3>
  <img src="https://github.com/user-attachments/assets/fe308854-0f99-4768-83b1-a91813715909">
</div>


<div>
  <hr>
  <h1>Tests automáticos</h1>
  <p>El proyecto está testeado en más del 80% de su totalidad.</p>
  <img src="https://github.com/user-attachments/assets/eaba2bd7-1f99-43f1-b06b-89342fd0a636">
  <p>Si se quiere verificar el código los tests, descargar el repositorio como zip y abrirlo con IntelliJ. Los tests se encuentran en "/src/tests".</p>
</div>


<div>
  <hr>
  <h1>Documentación</h1>
  <h2>Diagrama de secuencia "/personas/mutant/"</h2>
  <img src="https://github.com/user-attachments/assets/da589390-ee47-41c6-9e59-a9007d79debd">
  <h2>Diagrama de secuencia "/stats"</h2>
  <img src="https://github.com/user-attachments/assets/1d13f134-5d26-4297-879e-b0d4f0855fa5">
  <h3>Diagrama de la arquitectura del sistema</h3>
  <img src="https://github.com/user-attachments/assets/efc18cf8-3820-4d49-89e4-0b890bb90918">
</div>
