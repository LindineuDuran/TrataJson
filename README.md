# TrataJson
## Exemplo de Serialização e Desserialização de Objetos Java em JSON, e manipulação e listas de objetos
- Serializar objeto Java em JSON;
- Desserializar JSON em objeto Java;
- Ler JSONs de diversos formatos e desserializar nas listas de objetos corretas;
- Montar lista de objetos à partir de outras 3 listas;
- Serializar lista de objetos java em JSON;
- Salvar JSON gerado;
- Implementada a Desserialização de um Array JSON em uma Lista Genérica;
- [Convert string to OffsetDateTime in Java](https://stackoverflow.com/questions/44297939/convert-string-to-offsetdatetime-in-java)
<ul>
	<li>Jackson and generic type reference - https://stackoverflow.com/questions/6846244/jackson-and-generic-type-reference</li>
	<ul>
		<li>Tester method needs to have access to Class, and you can construct:<b><i> "JavaType type = mapper.getTypeFactory().constructCollectionType(List.class, Foo.class);"</i></b></li>
		<li>and then:<b><i> "List<Foo> list = mapper.readValue(new File("input.json"), type);"</i></b></li>
	</ul>
</li>
</ul>
