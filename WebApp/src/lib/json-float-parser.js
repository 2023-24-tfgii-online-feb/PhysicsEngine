export function parseJsonWithFloats(jsonString) {
    const floatRegex = /"(\w+)": "([-+]?[0-9]*\.?[0-9]+([eE][-+]?[0-9]+)?)/g;
    const jsonWithFloats = jsonString.replace(floatRegex, '"$1": $2');
    return JSON.parse(jsonWithFloats);
  }