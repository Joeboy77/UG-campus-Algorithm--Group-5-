import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AppFrame  extends JFrame implements ActionListener {
    private JLabel startLabel;
    private JLabel stopLabel;
    private JLabel route;
    private JButton button;
    private JFrame frame;
    private JPanel panel;
    private JComboBox comboBox1;
    private JComboBox comboBox2;

    public AppFrame() {
        frame = new JFrame("Transportation Application On UG Campus");
        panel = new JPanel();
        button = new JButton("Start");
        button.setBounds(40, 30, 50, 2);
        startLabel = new JLabel("Please choose your current location: ");
        stopLabel = new JLabel("Please choose your destination: ");

        String[] locations = {"Select a location", "CS Department", "Math Department", "Engineering School", "Chemistry Department", "Law Faculty", "JQB", "Main Gate", "School of Performing Arts", "Balme Library", "UGCS" , "Business School", "Volta Hall", "Commonwealth", "Great Hall", "Akuafo Hall", "Legon Hall", "Bush Canteen", "Sarbah Park", "Fire Station", "Banking Square", "Night Market", " Basic School", "Diaspora Halls"};
        comboBox1 = new JComboBox(locations);
        comboBox1.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedLocation = (String) comboBox1.getSelectedItem();
            }
        });
        comboBox1.setBounds(50, 50, 90, 20);
        comboBox1.setSelectedIndex(0);

        comboBox2 = new JComboBox(locations);
        comboBox2.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedLocation = (String) comboBox2.getSelectedItem();
            }
        });
        comboBox2.setBounds(50, 50, 90, 20);
        comboBox2.setSelectedIndex(0);


        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(new GridLayout(0, 1));
        panel.add(startLabel);
        panel.add(comboBox1);
        panel.add(stopLabel);
        panel.add(comboBox2);
        panel.add(button);

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        button.addActionListener(this);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

        String start = comboBox1.getSelectedItem().toString();
        String stop = comboBox2.getSelectedItem().toString();

       if(start.equals("Select a location") || stop.equals("Select a location")){
           JOptionPane.showMessageDialog(null, "Please select a location");
       }
       else if(start.equals(stop)){
           JOptionPane.showMessageDialog(null, "Please select a different location");
       }
       else{
           JOptionPane.showMessageDialog(null, "You selected: " + start + " as your starting location and " + stop + " as your destination");
       }

        Nodes startNode;
        Nodes stopNode;

        Graph graph = new Graph(true);
        Nodes engineeringSchool = new Nodes(0, "Engineering School");
        Nodes csDept = new Nodes(1, "CS Department");
        Nodes Law = new Nodes(2, "Law Faculty");
        Nodes JQB = new Nodes(3, "JQB");
        Nodes MainGate = new Nodes(4, "Main Gate");
        Nodes PerformingArts = new Nodes(5, "School of Performing Arts");
        Nodes MAthsandStats = new Nodes(6, "Math Department");
        Nodes Balme = new Nodes(7, "Balme Library");
        Nodes UGCS = new Nodes(8, "UGCS");
        Nodes BusinessSchool = new Nodes(9, "Business School");
        Nodes VoltaHall = new Nodes(10, "Volta Hall");
        Nodes Commonwealth = new Nodes(11, "Commonwealth Hostel");
        Nodes GreatHall = new Nodes(12, "Great Hall");
        Nodes AkuafoHall = new Nodes(13, "Akuafo Hall");
        Nodes LegonHall = new Nodes(14, "Legon Hall");
        Nodes BushCanteen = new Nodes(15, "Bush Canteen");
        Nodes SarbahPark = new Nodes(16, "Sarbah Park");
        Nodes FireService = new Nodes(17, "Fire Station");
        Nodes BankingSquare = new Nodes(18, "Banking Square");
        Nodes NightMarket = new Nodes(19, "Night Market");
        Nodes BasicSchool = new Nodes(20, "UG Basic School");
        Nodes Diaspora = new Nodes(21, "Diaspora Halls");

        startNode = engineeringSchool;
        stopNode = Diaspora;
        // Our addEdge method automatically adds Nodes as well.
        // The addNode method is only there for unconnected Nodes,
        // if we wish to add any
        graph.addEdge(engineeringSchool, csDept, 270.12);
        graph.addEdge(csDept, engineeringSchool, 270.12);

        graph.addEdge(engineeringSchool, Law, 420.88);
        graph.addEdge(Law, engineeringSchool, 420.88);

        graph.addEdge(engineeringSchool, JQB, 502.43);
        graph.addEdge(JQB, engineeringSchool, 502.43);

        graph.addEdge(csDept, Law, 346.45);
        graph.addEdge(Law, csDept, 346.45);

        graph.addEdge(Law, JQB, 289.39);
        graph.addEdge(JQB, Law, 289.39);

        graph.addEdge(csDept, MAthsandStats, 208.65);
        graph.addEdge(MAthsandStats, csDept, 208.65);

        graph.addEdge(MAthsandStats, UGCS, 653.88);
        graph.addEdge(UGCS, MAthsandStats, 653.88);

        graph.addEdge(UGCS, BusinessSchool, 407.81);
        graph.addEdge(BusinessSchool, UGCS, 407.81);

        graph.addEdge(BusinessSchool, VoltaHall, 346.82);
        graph.addEdge(VoltaHall, BusinessSchool, 346.82);

        graph.addEdge(VoltaHall, Commonwealth, 536.69);
        graph.addEdge(Commonwealth, VoltaHall, 536.69);

        graph.addEdge(Commonwealth, GreatHall, 586.81);
        graph.addEdge(GreatHall, Commonwealth, 586.81);

        graph.addEdge(MainGate, PerformingArts, 50.00);
        graph.addEdge(PerformingArts, MainGate, 50.00);

        graph.addEdge(PerformingArts, Balme, 992.04);
        graph.addEdge(Balme, PerformingArts, 992.04);

        graph.addEdge(UGCS, Balme, 269.71);
        graph.addEdge(Balme, UGCS, 269.71);

        graph.addEdge(Balme, AkuafoHall, 316.59);
        graph.addEdge(AkuafoHall, Balme, 316.59);

        graph.addEdge(Balme, Commonwealth, 520);
        graph.addEdge(Commonwealth, Balme, 520);

        graph.addEdge(PerformingArts, AkuafoHall, 701.74);
        graph.addEdge(AkuafoHall, PerformingArts, 701.74);

        graph.addEdge(Balme, LegonHall, 586.81);
        graph.addEdge(LegonHall, Balme, 586.81);

        graph.addEdge(LegonHall, AkuafoHall, 100);
        graph.addEdge(AkuafoHall, LegonHall, 100);

        graph.addEdge(LegonHall, BasicSchool, 1015.00);
        graph.addEdge(BasicSchool, LegonHall, 1015.00);

        graph.addEdge(LegonHall, SarbahPark, 500.00);
        graph.addEdge(SarbahPark, LegonHall, 500.00);

        graph.addEdge(AkuafoHall, SarbahPark, 200.00);
        graph.addEdge(SarbahPark, AkuafoHall, 200.00);

        graph.addEdge(BasicSchool, NightMarket, 591.36);
        graph.addEdge(NightMarket, BasicSchool, 591.36);

        graph.addEdge(NightMarket, Diaspora, 645.28);
        graph.addEdge(Diaspora, NightMarket, 645.28);

        graph.addEdge(NightMarket, BankingSquare, 1);
        graph.addEdge(BankingSquare, NightMarket, 1);

        graph.addEdge(NightMarket, BankingSquare, 957.14);
        graph.addEdge(BankingSquare, NightMarket, 957.14);

        graph.addEdge(BushCanteen, FireService, 122.85);
        graph.addEdge(FireService, BushCanteen, 122.85);

        graph.addEdge(FireService, BankingSquare, 957.14);
        graph.addEdge(BankingSquare, FireService, 957.14);

        //System.out.println("Input current location");

        String currentLocation = start;

        switch (currentLocation) {
            case "Engineering Department":
                startNode = engineeringSchool;
                break;
            case "CS Department":
                startNode = csDept;
                break;
            case "Law Faculty":
                startNode = Law;
                break;
            case "JQB":
                startNode = JQB;
                break;
            case "Math Department":
                startNode = MAthsandStats;
                break;
            case "Main Gate":
                startNode = MainGate;
                break;
            case "School of Performing Arts":
                startNode = PerformingArts;
                break;
            case "Balme Library":
                startNode = Balme;
                break;
            case "UGCS":
                startNode = UGCS;
                break;
            case "Business School":
                startNode = BusinessSchool;
                break;
            case "Volta Hall":
                startNode = VoltaHall;
                break;
            case "Commonwealth":
                startNode = Commonwealth;
                break;
            case "Great Hall":
                startNode = GreatHall;
                break;
            case "Akuafo Hall":
                startNode = AkuafoHall;
                break;
            case "Legon Hall":
                startNode = LegonHall;
                break;
            case "Sarbah Park":
                startNode = SarbahPark;
                break;
            case "Basic School":
                startNode = BasicSchool;
                break;
            case "Night Market":
                startNode = NightMarket;
                break;
            case "Diaspora Halls":
                startNode = Diaspora;
                break;
            case "Bush Canteen":
                startNode = BushCanteen;
                break;
            case "Fire Station":
                startNode = FireService;
                break;
            case "Banking Square":
                startNode = BankingSquare;
                break;

        }


        String destination = stop;

        switch (destination) {
            case "Engineering School":
                stopNode = engineeringSchool;
                break;

            case "CS Department":
                stopNode = csDept;
                break;
            case "Law Faculty":
                stopNode = Law;
                break;
            case "JQB":
                stopNode = JQB;
                break;
            case "Math Department":
                stopNode = MAthsandStats;
                break;
            case "Main Gate":
                stopNode = MainGate;
                break;
            case "School of Performing Arts":
                stopNode = PerformingArts;
                break;
            case "Balme Library":
                stopNode = Balme;
                break;
            case "UGCS":
                stopNode = UGCS;
                break;
            case "Business School":
                stopNode = BusinessSchool;
                break;
            case "Volta Hall":
                stopNode = VoltaHall;
                break;
            case "Commonwealth":
                stopNode = Commonwealth;
                break;
            case "Great Hall":
                stopNode = GreatHall;
                break;
            case "Akuafo Hall":
                stopNode = AkuafoHall;
                break;
            case "Legon Hall":
                stopNode = LegonHall;
                break;
            case "Sarbah Park":
                stopNode = SarbahPark;
                break;
            case "Basic School":
                stopNode = BasicSchool;
                break;
            case "Night Market":
                stopNode = NightMarket;
                break;
            case "Diaspora Halls":
                stopNode = Diaspora;
                break;
            case "Bush Canteen":
                stopNode = BushCanteen;
                break;
            case "Fire Station":
                stopNode = FireService;
                break;

            case "Banking Square":
                stopNode = BankingSquare;
                break;
        }

        String path = graph.shortestPath(startNode, stopNode);
        route = new JLabel(path);
        panel.add(route);
    }
}