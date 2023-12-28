export function drawBody(p, body) {
    const { position, radius, bodyType, vertices, selected } = body;
  
    if (bodyType === "ASTEROID") {
      drawAsteroid(p, position, vertices, selected);
    } else if (bodyType === "SPACESHIP") {
      drawSpaceship(p, position, selected);
    }
    else {
      if (selected) {
        p.fill(255, 234, 0);
      } else {
        p.noFill();
      }
      p.stroke(255);
      p.strokeWeight(2);
      p.ellipse(position.x, position.y, radius * 2, radius * 2);
    }
  }
  
  function drawAsteroid(p, position, vertices, selected) {
    if (selected) {
      p.fill(255, 234, 0);
    } else {
      p.noFill();
    }
    p.stroke(255);
    p.strokeWeight(2);
    //console.log("MY_DEBUG: Drawing asteroid", position, vertices);
    p.beginShape();
    for (const vertex of vertices) {
      p.vertex(vertex.x + position.x, vertex.y + position.y);
    }
    p.endShape(p.CLOSE);
  }

  function drawSpaceship(p, position, selected){
    if (selected) {
      p.fill(255, 234, 0);
    } else {
      p.fill(0,0,255);
    }
    p.stroke(255);
    p.strokeWeight(2);
    p.ellipse(position.x, position.y, 10 * 2, 10 * 2);
  }