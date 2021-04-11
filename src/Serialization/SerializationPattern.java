package Serialization;

import Model.*;
import Model.Reader;

import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class works as a Singleton, which serialize data
 * Thanks to this class our program can access needed data anywhere without passing them into controllers constructors
 * Inspiration for Singleton https://www.geeksforgeeks.org/singleton-class-java/
 * Inspiration for serialization from https://www.tutorialspoint.com/java/java_serialization.htm
 */
public class SerializationPattern {
    private static final Logger LOGGER = Logger.getLogger(SerializationPattern.class.getName());

    private static SerializationPattern INSTANCE = null;

    private ObjectForSerialization serializationObject;

    // Store all arraylists in one object for better serialization
    public static class ObjectForSerialization implements Serializable {
        // needed lists for program
        private final ArrayList<Librarian> allLibrarians;
        private final ArrayList<Reader> allReaders;
        private final ArrayList<BorrowingRecord> allBorrowingRecords;
        private final ArrayList<BookTitle> allBookTitles;
        private final ArrayList<BookCopy> allBookCopies;

        public ObjectForSerialization() {
            this.allLibrarians = new ArrayList<>();
            this.allReaders = new ArrayList<>();
            this.allBorrowingRecords = new ArrayList<>();
            this.allBookTitles = new ArrayList<>();
            this.allBookCopies = new ArrayList<>();
        }

        public ArrayList<Librarian> getAllLibrarians() {
            return allLibrarians;
        }

        public ArrayList<Reader> getAllReaders() {
            return allReaders;
        }

        public ArrayList<BorrowingRecord> getAllBorrowingRecords() {
            return allBorrowingRecords;
        }

        public ArrayList<BookTitle> getAllBookTitles() {
            return allBookTitles;
        }

        public ArrayList<BookCopy> getAllBookCopies() {
            return allBookCopies;
        }
    }

    // when unique SerializationPattern object is created, program deserialize it's data
    private SerializationPattern() {
        try (FileInputStream fileIn = new FileInputStream("library_data.ser");
             ObjectInputStream in = new ObjectInputStream(fileIn))
        {
            LOGGER.info("Getting serialized data from /library_data.ser");
            this.serializationObject = (ObjectForSerialization) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
            // NOTE: SEPARATE THIS EXCEPTIONS
            // if there was an exception, then create new observable list for program
            LOGGER.info("Creating new arraylists\n" +
                    "Something might went wrong\n" +
                    "or there wasn't any data in file library_data.ser,\n" +
                    "which are used for serialization."
            );
            this.setSerializationObject(new ObjectForSerialization());
        }
    }

    // this method serialize whole serializationObject, whenever it is called
    public void serializeData() {
        // get serialized data from /library_data.ser
        try (FileOutputStream fileOut = new FileOutputStream("library_data.ser");
             ObjectOutputStream out = new ObjectOutputStream(fileOut))
        {
            LOGGER.info("Serializing data to /library_data.ser");
            out.writeObject(this.serializationObject);
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.log(Level.SEVERE, e.getMessage());
        }
    }

    // this method makes sure, that there is only one instance of this class in whole program
    public static SerializationPattern getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SerializationPattern();
        }
        return INSTANCE;
    }

    public void addNewReader(Reader newReader) {
        // add newReader to arraylist with all readers
        this.getSerializationObject().getAllReaders().add(newReader);
        // serialize data, because new reader was added
        this.serializeData();
    }

    public void addNewBookTitle(BookTitle newBookTitle) {
        // add newBookTitle to arraylist with all book titles
        this.getSerializationObject().getAllBookTitles().add(newBookTitle);
        // serialize data, because new book title was added
        this.serializeData();
    }

    public void addNewBookCopy(BookCopy newBookCopy) {
        // add newBookCopy to arraylist with all book copies
        this.getSerializationObject().getAllBookCopies().add(newBookCopy);
        // serialize data, because new book copy was added
        this.serializeData();
    }

    public ObjectForSerialization getSerializationObject() {
        return serializationObject;
    }

    public void setSerializationObject(ObjectForSerialization serializationObject) {
        this.serializationObject = serializationObject;
    }
}
