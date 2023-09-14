import { drawBody } from './draw.js';
import { parseJsonWithFloats } from './json-parser.js';


let bodies = [];
let connectionEstablished = false;
const socket = new SockJS('/physics-engine-websocket');
const stompClient = Stomp.over(socket);

function setupWebSocket() {
  stompClient.connect({}, () => {
    connectionEstablished = true;
    stompClient.subscribe("/topic/bodies", (message) => {
      const updatedBodies = parseJsonWithFloats(message.body);
      bodies.length = 0; // Clear the existing bodies array
      Array.prototype.push.apply(bodies, updatedBodies); // Add the new bodies to the array

      console.log(`<< MESSAGE\n${message.headers['destination']}\nBodies received: ${updatedBodies.length}`);
    });
  });

  document
      .getElementById("getRandomBody")
      .addEventListener("click", addRandomBody);
  document
      .getElementById("getRandomPlanet")
      .addEventListener("click", addRandomPlanet);
  document
      .getElementById("getRandomAsteroid")
      .addEventListener("click", addRandomAsteroid);
}

const sketch = (p) => {
  p.setup = () => {
    p.createCanvas(1240,720);
    setupWebSocket();
  };

  p.draw = () => {
    if (!connectionEstablished) {
      return;
    }

    requestBodies();
    p.background(255);

    // Draw all received bodies
    for (const body of bodies) {
      drawBody(p, body);
    }

  };
};

function requestBodies() {
    stompClient.send("/app/retrieve-bodies", {}, "");
}

function addRandomBody() {
  stompClient.send("/app/random-body", {}, "Requested a random body.");
}
function addRandomPlanet() {
  stompClient.send("/app/random-planet", {}, "Requested a random planet.");
}
function addRandomAsteroid() {
  stompClient.send("/app/random-asteroid", {}, "Requested a random asteroid.");
}

const myP5 = new p5(sketch, "canvas-container");
