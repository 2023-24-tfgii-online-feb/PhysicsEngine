const figures = [];

function setupWebSocket() {
  const socket = new SockJS(`${apiUrl}/physics-engine-websocket`);
  const stompClient = Stomp.over(socket);

  stompClient.connect({}, () => {
    stompClient.subscribe('/topic/bodies', (message) => {
      const body = JSON.parse(message.body);
      figures.push(body);
    });
  });

  document.getElementById('getRandomBody').addEventListener('click', () => {
    stompClient.send('/app/random-body', {}, 'Requested a random body.');
  });
}

const sketch = (p) => {
  
    p.setup = () => {
      p.createCanvas(800, 600);

      setupWebSocket();
  
      // TODO: Move function body to a genRandomFigure() function.
      document.getElementById('addRandomFigure').addEventListener('click', () => {
        const randomRadius = Math.random() * 50 + 20;
        const randomX = Math.random() * (p.width - randomRadius) + randomRadius / 2;
        const randomY = Math.random() * (p.height - randomRadius) + randomRadius / 2;
        const randomVelocityX = Math.random() * 4 - 2;
        const randomVelocityY = Math.random() * 4 - 2;
      
        figures.push({
          position: { x: randomX, y: randomY },
          radius: randomRadius,
          velocity: { x: randomVelocityX, y: randomVelocityY },
          mass: randomRadius * 10,
        });
      });
      
    }
  
    p.draw = () => {
      p.background(255);
    
      // Draw all received bodies
      for (const body of figures) {
        drawBody(p, body);
      }
    };
    console.log("Tick.")
  };

  function drawBody(p, body) {
    const { position, radius } = body;
  
    // Draw the body as a circle with a border
    p.noFill();
    p.stroke(0);
    p.strokeWeight(2);
    p.ellipse(position.x, position.y, radius * 2, radius * 2);
  }
  
  
  const myP5 = new p5(sketch, 'canvas-container');
  
  