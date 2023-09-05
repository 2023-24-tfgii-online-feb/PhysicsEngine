export function drawBody(p, body) {
    const { position, radius, type, vertices } = body;
  
    if (type === 'ASTEROID') {
      drawAsteroid(p, position, vertices);
    } else {
      // Draw the body as a circle with a border (for planets and stars)
      p.noFill();
      p.stroke(0);
      p.strokeWeight(2);
      p.ellipse(position.x, position.y, radius * 2, radius * 2);
    }
  }
  
  function drawAsteroid(p, position, vertices) {
    p.noFill();
    p.stroke(0);
    p.strokeWeight(2);
  
    p.beginShape();
    for (const vertex of vertices) {
      p.vertex(vertex.x + position.x, vertex.y + position.y);
    }
    p.endShape(p.CLOSE);
  }
  
