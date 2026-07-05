package Trie;

public class TrieMain {
    public static void main(String[] args) {
        Trie trie = new Trie();
        String[] word = {"and", "ant", "do", "dad"};

        for (String s : word) {
            trie.insert(s);
        }

        String[] searchWord = { "do", "gee", "bat" };

        for (String s : searchWord) {
            if (trie.search(s))
                System.out.print("true ");
            else
                System.out.print("false ");
        }
        System.out.println();

        String[] prefixKeys = { "ge", "ba", "do", "de" };
        for (String s : prefixKeys) {
            if (trie.isPrefix(s))
                System.out.print("true ");
            else
                System.out.print("false ");
        }
    }
}
