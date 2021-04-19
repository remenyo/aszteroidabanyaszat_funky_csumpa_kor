package src;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Filekezelo {


	/**
	 * Megkeresi a tesztelendő fájlokat.
	 * 
	 * @param argumentumok mappa vagy file név
	 * @return List<File> a fájlok listája
	 */
	static List<File> fajlListazas(String... argumentumok) {
		List<File> files_to_test = new ArrayList<File>();

		Boolean arg_should_be_dir = false;
		String dir = "";
		if (argumentumok.length == 0) {
			arg_should_be_dir = true;
			dir = System.getProperty("user.dir");
		} else if (argumentumok.length == 1) {
			arg_should_be_dir = !argumentumok[0].contains(".");
			dir = argumentumok[0];
		}
		if (arg_should_be_dir) {
			Szkeleton.dir_to_save = dir;
			try (Stream<Path> stream = Files.list(Paths.get(dir))) {
				files_to_test = stream.filter(file -> {
					if (Files.isDirectory(file))
						return false;
					String name = file.getFileName().toString();
					return (!name.contains("elvart")) && (!name.contains("eredmeny")
							&& name.substring(name.lastIndexOf(".") + 1).equals("txt"));
				}).map(Path::toFile).collect(Collectors.toList());
			} catch (Exception e) {
				Log.error(e.toString());
			}
		} else {// argumentumok.length >= 2 --> mind file
			Szkeleton.dir_to_save = "";
			for (String fileName : argumentumok) {
				try {
					File f = Paths.get(System.getProperty("user.dir"), fileName).toFile();
					if (f.exists()) {
						files_to_test.add(f);
					} else {
						Log.warn("A file nem található: " + f.getAbsolutePath());
					}
				} catch (Exception e) {
					Log.error(e.toString());
				}
			}
		}
		return files_to_test;
	}


	/**
	 * A régi tesztnev_eredmeny.txt-ket átrakja az eredmenyek mappába (és ha kell át is nevezi
	 * őket.)
	 * 
	 * @param files az áthelyezendő eredmeny fájlok
	 */
	static void athelyez_regi_eredmenyek(List<File> files) {
		Path cel = null;
		try {
			for (File file_to_test : files) {
				cel = Files
						.createDirectories(
								Paths.get(
										file_to_test.getAbsolutePath().substring(0,
												file_to_test.getAbsolutePath().length()
														- file_to_test.getName().length()),
										"eredmenyek"));
				File eredmeny = Paths
						.get(file_to_test.getAbsolutePath().substring(0,
								file_to_test.getAbsolutePath().length() - 4) + "_eredmeny.txt")
						.toFile();
				if (!eredmeny.exists()) {
					continue;
				}
				Path dest = Paths.get(cel.toAbsolutePath().toString(), eredmeny.getName());
				Path src = Paths.get(eredmeny.getAbsolutePath());
				while (Files.exists(dest)) {
					dest = Paths
							.get(cel.toString(),
									dest.getFileName().toString().substring(0,
											dest.getFileName().toString().length() - 4)
											+ "_ujabb.txt");
				}
				Files.move(src, dest);
			}
		} catch (Exception e) {
			Log.error(e.toString());
		}
	}


	/**
	 * A függvény automatikusan lefuttatja a tesztet.
	 * 
	 * @param file a file aminek generálni kell az eredményét
	 */
	static void auto_teszt_futtatas(File file) {
		try (Stream<String> nyers_sorok = Files.lines(file.toPath())) {
			List<String> sorok = nyers_sorok.map(l -> l.replaceAll(l, l.replaceAll("\\s+", "")))
					.collect(Collectors.toList());
			for (String sor : sorok) {
				Log.debug(sor);
				String[] argumentumok = sor.split(":");
				if (argumentumok.length == 1) {
					Szkeleton.parancs(argumentumok[0]);
				} else if (argumentumok.length >= 2) {
					Szkeleton.parancs(argumentumok[0],
							Arrays.copyOfRange(argumentumok, 1, argumentumok.length));
				}
				if (Szkeleton.inkonzisztens_allapot) {
					break;
				}
			}
		} catch (Exception e) {
			Log.error(e.toString());
		}
	}

}
