export function drawBody(p, body) {
    const { position, radius, bodyType, vertices } = body;
  
    if (bodyType === "ASTEROID") {
      drawAsteroid(p, position, vertices);
    } else if (bodyType === "SPACESHIP") {
      drawSpaceship(p, position);
    }
    else { 
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
    //console.log("MY_DEBUG: Drawing asteroid", position, vertices);
    p.beginShape();
    for (const vertex of vertices) {
      p.vertex(vertex.x + position.x, vertex.y + position.y);
    }
    p.endShape(p.CLOSE);
  }

  function drawSpaceship(p, position){
    p.fill(255, 165, 0);
    p.stroke(0);
    p.strokeWeight(2);
    p.ellipse(position.x, position.y, 10 * 2, 10 * 2);
  }
  
