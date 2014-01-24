import java.io.File;

public class FiltreExtension extends javax.swing.filechooser.FileFilter
{
	// 2 chaînes qui contiendront respectivementl’entension du fichier (de la forme "jpg", "txt", etc…) et la descritpion
	// par exemple "Fichier JPEG" ou "Fichier Texte Brut".
	String extension;
	String description;

	// Notre constructeur qui admet donc comme arguments l’extension souhaitée ainsi que la description que l’on lui associe
	public FiltreExtension(String extension, String description)
	{
		// on n’a ainsi pas besoin (on ne doit pas) inclure le "." (point) dans le libellé de l’extension
		if (extension.indexOf("'") == -1)
			extension = "." + extension;
		// on assimile les Strings du départ (variables de classe) aux Strings fournis comme argument au constructeur (variableslocales)
		this.extension = extension;
		this.description = description;
	}

	public boolean accept(File fichier)
	{
		// vérifier si le le fichier finit avec l’extension que nous avons spécifié dans le constructeur, et, dans ce cas "accepter" de l’afficher
		// dans la fenêtre du JFileChooser
		if (fichier.getName().endsWith(extension))
			return true;
		// les répertoires aussi doivent êtreaffichés dans la fenêtre du JFileChooser
		else if (fichier.isDirectory())
			return true;
		return false;
	}

	public String getDescription()
	{
		// la description du fichier, que l’on associe à son extension, on a un affichage du type: "Fichier JPEG (*.jpg)"
		return this.description + "(*" + extension + ")";
	}
}