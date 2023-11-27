package za.co.varsitycollege.st10090417.churchcommunity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager


class MainActivity : ComponentActivity() {
    // Variables to store devotionals or Bible readings
    private val devotionals = listOf(
        "Romans 8:31 \n What, then, shall we say in response to these things? If God is for us, who can be against us ",
        "Deuteronomy 31:6 \n Be strong and courageous. Do not be afraid or terrified because of them, or the Lord your God goes with you; he will never leave you nor forsake you.\n ",
        "Psalm 27:1 \n The Lord is my light and my salvation; whom shall I fear? The Lord is the stronghold of my life; of whom shall I be afraid?\n ",
        "1 John 4:18 \n There is no fear in love. But perfect love drives out fear, because fear has to do with punishment. The one who fears is not made perfect in love.\n ",
        "Psalm 31:24 \n Be strong, and let your heart take courage, all you who wait for the Lord!\n ",
        "Isaiah 40:31 \n But they who wait for the Lord shall renew their strength; they shall mount up with wings like eagles; they shall run and not be weary; they shall walk and not faint.\n ",
        "John 15:13 \n Greater love has no one than this: to lay down one's life for one's friends.\n ",
        "Philippians 4:6-8\nDo not be anxious about anything, but in every situation, by prayer and petition, with thanksgiving, present your requests to God. And the peace of God, which transcends all understanding, will guard your hearts and your minds in Christ Jesus. Finally, brothers and sisters, whatever is true, whatever is noble, whatever is right, whatever is pure, whatever is lovely, whatever is admirable—if anything is excellent or praiseworthy—think about such things.",
        "Romans 12:2\nDo not conform to the pattern of this world, but be transformed by the renewing of your mind. Then you will be able to test and approve what God’s will is—his good, pleasing and perfect will.",
        "Romans 8:28\nAnd we know that in all things God works for the good of those who love him, who have been called according to his purpose.",
        "Psalm 23\nThe Lord is my shepherd; I shall not want. He makes me lie down in green pastures. He leads me beside still waters. He restores my soul. He leads me in paths of righteousness for his name's sake. Even though I walk through the valley of the shadow of death, I will fear no evil, for you are with me; your rod and your staff, they comfort me. You prepare a table before me in the presence of my enemies; you anoint my head with oil; my cup overflows. Surely goodness and mercy shall follow me all the days of my life, and I shall dwell in the house of the Lord forever.",
        "Jeremiah 29:11\nFor I know the plans I have for you,” declares the Lord, “plans to prosper you and not to harm you, plans to give you hope and a future.",
        "Ephesians 6:12\nFor our struggle is not against flesh and blood, but against the rulers, against the authorities, against the powers of this dark world and against the spiritual forces of evil in the heavenly realms.",
        "Joshua 1:9 \nHave I not commanded you? Be strong and courageous. Do not be afraid; do not be discouraged, for the Lord your God will be with you wherever you go.",
        "John 16:33\nI have told you these things, so that in me you may have peace. In this world you will have trouble. But take heart! I have overcome the world.",
        "Isaiah 40:31\nBut those who hope in the Lord will renew their strength. They will soar on wings like eagles; they will run and not grow weary, they will walk and not be faint.",
        "Galatians 5:22-23\nBut the fruit of the Spirit is love, joy, peace, patience, kindness, goodness, faithfulness, gentleness and self-control. Against such things there is no law.",
        "2 Chronicles 7:14\nIf my people, who are called by my name, will humble themselves and pray and seek my face and turn from their wicked ways, then I will hear from heaven, and I will forgive their sin and will heal their land.",
        "Matthew 28:19-20\nTherefore go and make disciples of all nations, baptizing them in the name of the Father and of the Son and of the Holy Spirit, and teaching them to obey everything I have commanded you. And surely I am with you always, to the very end of the age.",
        " 1 Corinthians 10:13\nNo temptation has overtaken you except what is common to mankind. And God is faithful; he will not let you be tempted beyond what you can bear. But when you are tempted, he will also provide a way out so that you can endure it."
    )

    private var currentDevotionalIndex = 0

    // Variables to store prayer requests
    private val prayerRequests = mutableListOf<String>()

    // UI components
    private lateinit var devotionalTextView: TextView
    private lateinit var prevButton: Button
    private lateinit var nextButton: Button
    private lateinit var prayerEditText: EditText
    private lateinit var addPrayerButton:Button
    private lateinit var prayer: ListView
    private lateinit var prayerList: ArrayList<String>



    @SuppressLint("CutPasteId", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize UI components
        devotionalTextView = findViewById(R.id.devotionalTextView)
        prevButton = findViewById(R.id.prevButton)
        nextButton = findViewById(R.id.nextButton)
        prayer = findViewById(R.id.prayer)
        addPrayerButton = findViewById(R.id.addPrayerButton)
        prayerEditText = findViewById(R.id.prayerEditText)
        prayerList = ArrayList()


        //back btn intent to the main activity
        val backbtn = findViewById<ImageView>(R.id.backbtn)
        backbtn.setOnClickListener {
            val intent1 = Intent(this, MainActivity::class.java)
            //call the intent
            startActivity(intent1)

        }

        // Load the first devotional
        loadDevotional()

        // Set click listeners for prev and next buttons
        prevButton.setOnClickListener { showPreviousDevotional() }
        nextButton.setOnClickListener { showNextDevotional() }



        // we are initializing adapter for our list view.
        val adapter: ArrayAdapter<String?> = ArrayAdapter<String?>(
            this@MainActivity,
            android.R.layout.simple_list_item_1,
            prayerList as List<String?>
        )

        // we are setting adapter for our list view.
        prayer.adapter = adapter

        // adding click listener for our button.
        addPrayerButton.setOnClickListener {

            // getting text from edit text
            val item = prayerEditText.text.toString()

            // checking if item is not empty
            if (item.isNotEmpty()) {
                // on below line we are adding item to our list.
                prayerList.add(item)

                //we are notifying adapter
                // that data in list is updated to update our list view.
                adapter.notifyDataSetChanged()
            }
            else {
                Toast.makeText(this@MainActivity, "Please enter prayer", Toast.LENGTH_SHORT).show()
            }

            //clear edit text
            prayerEditText.setText("")

        }
    }
    private fun loadDevotional() {
        devotionalTextView.text = devotionals[currentDevotionalIndex]
    }

    private fun showPreviousDevotional() {
        if (currentDevotionalIndex > 0) {
            currentDevotionalIndex--
            loadDevotional()
        }
    }

    private fun showNextDevotional() {
        if (currentDevotionalIndex < devotionals.size - 1) {
            currentDevotionalIndex++
            loadDevotional()
        }
    }





}

