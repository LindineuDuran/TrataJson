# TrataJson
## Exemplo de Serialização e Desserialização de Objetos Java em JSON, e manipulação e listas de objetos
- Serializar objeto Java em JSON;
- Desserializar JSON em objeto Java;
- Ler JSONs de diversos formatos e desserializar nas listas de objetos corretas;
- Montar lista de objetos à partir de outras 3 listas;
- Serializar lista de objetos java em JSON;
- Salvar JSON gerado;
- Implementada a Desserialização de um Array JSON em uma Lista Genérica;
- 
<li>[Jackson and generic type reference](https://stackoverflow.com/questions/6846244/jackson-and-generic-type-reference)
  <ul>
	   <li>Tester method needs to have access to Class, and you can construct: "JavaType type = mapper.getTypeFactory().constructCollectionType(List.class, Foo.class);"</li>
	   <li>and then: "List<Foo> list = mapper.readValue(new File("input.json"), type);"</li>
  </ul>
</li>
