package br.com.llduran.tratajson.util;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileHandler
{
	public static String readFile(String filePath) throws IOException
	{
		Path path = Paths.get(filePath);
		Stream<String> lines = Files.lines(path, StandardCharsets.UTF_8);
		String data = lines.collect(Collectors.joining("\r\n"));

		return data;
	}

	public static void writeStream(String filePath, String dados)
	{
		Path path = Paths.get(filePath);

		if(dados.length() > 0)
		{
			try
			{
				Files.write(path, Collections.singleton(dados));
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}
}
