# Java Word Counter

A beginner Java console app that counts words in a line of text or a text file. It shows total and unique word counts, displays the most frequent words, and lets you look up individual words.

## Run

Install a JDK, then run these commands from the repository folder:

```bash
javac Main.java WordCounter.java
java Main
```

Enter text when prompted. To read a file instead:

```bash
java Main path/to/file.txt
```

After counting, enter how many top words to display. Type `quit` to end the lookup loop.

## What I'm practicing

- Reading console input and files with `Scanner`
- Working with strings, `ArrayList`, and `HashMap`
- Counting frequencies and sorting results

The current word cleaning keeps English letters and spaces; punctuation and digits are removed.
