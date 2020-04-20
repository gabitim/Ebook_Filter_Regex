import java.io.File

// @author: Timofti Gabriel

class StoryCleaner() {

    public fun cleanStory(poveste: String): String {

        //elimina \n multiplu
        var poveste = poveste.replace("(?m)^\\s*\\n".toRegex(), "")

        //elimina Numele Capitolui(care presupunem se afla pe o singur linie si contine kewword "Capitolul")
        poveste = poveste.replace("\\s+Capitolul.*\\s+".toRegex(), "\n")

        //eliminam numarul paginii(presupunem ca se afla pe o singura linie fara nimic altceva )
        poveste = poveste.replace("\\s+\\d+\\s+".toRegex(), "\n")

        //eliminam spatiile,tabs si alte delimitatoare
        val lines: List<String> = poveste.split("\n")

        poveste = ""
        for (line in lines) {
            var line = line.replace("\\s+".toRegex(), " ")
            //println(line)
            poveste += "$line \n"
        }

        return poveste

    }

   public  fun readFromFile(fp: File) : String {
        return fp.inputStream().readBytes().toString(Charsets.UTF_8)
    }
}

fun main() {
    val readPath = File("Ebook.txt")

    //object of type Tema
    val storyCleaner = StoryCleaner();

    //poveste is first a raw string ebook
    var poveste: String = storyCleaner.readFromFile(readPath)

    //clean story method transforms the string
    poveste = (storyCleaner.cleanStory(poveste))

    //we print the clean story
    print(poveste)
}