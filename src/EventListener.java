import java.util.*;

class Event {
    int userId;
    EventType eventType;

    Event(int userId, EventType eventType) {
        this.userId = userId;
        this.eventType = eventType;
    }
}

enum EventType {
    ADD_TO_CART,
    REMOVE_FROM_CART,
    VIEW_PRODUCT,
    PURCHASE,
    LOGIN,
    LOGOUT,
    VIEW_PAGE;
}

class Journey {
    List<EventType> eventTypes;

    Journey(List<EventType> eventTypes) {
        this.eventTypes = eventTypes;
    }

    public int size() {
        if (eventTypes == null) {
            return 0;
        }

        return eventTypes.size();
    }
}

class JourneyComparator implements Comparator<Journey> {

    @Override
    public int compare(Journey o1, Journey o2) {
        return o1.eventTypes.size() - o2.eventTypes.size();
    }
}

class UserJourney {
    private static int DEFAULT_INDEX = -1;
    int endIndex;
    List<EventType> journeyEvents;
    int userId;

    private static Set<EventType> RELEVANT_EVENT_TYPES_FOR_JOURNEY = new HashSet<EventType>(
            Arrays.asList(EventType.ADD_TO_CART, EventType.VIEW_PRODUCT, EventType.PURCHASE)
    );

    public UserJourney(int userId) {
        this.userId = userId;
        this.endIndex = DEFAULT_INDEX;
        this.journeyEvents = new ArrayList<>();
    }

    public void addEvent(Event event) {
        if (RELEVANT_EVENT_TYPES_FOR_JOURNEY.contains(event.eventType)) {
            journeyEvents.add(event.eventType);
            if (event.eventType == EventType.PURCHASE) {
                endIndex = journeyEvents.size()-1;
            }
        }
    }

    public Journey getJourney() {
        if (journeyEvents == null || endIndex == DEFAULT_INDEX) {
            return null;
        }

        return new Journey(journeyEvents.subList(0, endIndex+1));
    }
}

public class EventListener {

    UserJourney userJourney;
    PriorityQueue<Journey> topKJourneys;
    int reportSize;

    public EventListener(int reportSize) {
        this.reportSize = reportSize;
    }

    public void handleEvent(Event event) {
        if (userJourney == null) {
            userJourney = new UserJourney(event.userId);
        }
        if (event.userId != userJourney.userId) {
            addNewJourney(topKJourneys, userJourney.getJourney(), reportSize);
            userJourney = new UserJourney(event.userId);
        }

        userJourney.addEvent(event);
    }

    public List<Journey> getKLongestJourneys() {
        return new ArrayList<>(topKJourneys);
    }

//    public List<Journey> retrieveKLongestSuccessfulJourneys(List<EventType> userEventTypes, int k) {
//
//        if (userEventTypes == null) {
//            return null;
//        }
//
//        PriorityQueue<Journey> topKJourneys = new PriorityQueue<>(k, new JourneyComparator());
//
//        int startIndex = DEFAULT_INDEX;
//        int endIndex = DEFAULT_INDEX;
//
//        for (int i = 0; i < userEventTypes.size(); i++) {
//            EventType eventType = userEventTypes.get(i);
//            if (RELEVANT_EVENT_TYPES_FOR_JOURNEY.contains(eventType)) {
//                if (startIndex == DEFAULT_INDEX) {
//                    startIndex = i;
//                }
//                if (eventType == EventType.PURCHASE) {
//                    endIndex = i;
//                }
//
//            } else {
//                addNewJourney(topKJourneys, userEventTypes, startIndex, endIndex, k);
//                startIndex = DEFAULT_INDEX;
//                endIndex = DEFAULT_INDEX;
//            }
//        }
//
//        addNewJourney(topKJourneys, userEventTypes, startIndex, endIndex, k);
//
//        return new ArrayList<>(topKJourneys);
//    }

    private void addNewJourney(PriorityQueue<Journey> topKJourneys, Journey journey, int k) {
        if (topKJourneys == null) {
            topKJourneys = new PriorityQueue<>(k, new JourneyComparator());
        }

        if (topKJourneys.size() == k) {
            if (topKJourneys.peek().size() < journey.size()) {
                topKJourneys.poll();
                topKJourneys.add(journey);
            }
        } else {
            topKJourneys.add(journey);
        }
    }

//    private void addNewJourney(PriorityQueue<Journey> topKJourneys, List<EventType> userEventTypes, int startIndex,
//                               int endIndex, int k) {
//        if (endIndex != DEFAULT_INDEX) {
//            Journey journey = new Journey(userEventTypes.subList(startIndex, endIndex+1));
//            if (topKJourneys.size() == k) {
//                if (topKJourneys.peek().size() < endIndex-startIndex+1) {
//                    topKJourneys.poll();
//                    topKJourneys.add(journey);
//                }
//            } else {
//                topKJourneys.add(journey);
//            }
//        }
//    }

//    public List<Journey> retrieveSuccessfulJourneys(List<Event> userEvents) {
//
//        if (userEvents == null) {
//            return null;
//        }
//
//        List<Journey> journeys = new ArrayList<>();
//
//        int startIndex = DEFAULT_INDEX;
//        int endIndex = DEFAULT_INDEX;
//
//        for (int i=0; i < userEvents.size(); i++) {
//            Event event = userEvents.get(i);
//            if (journeyEvents.contains(event)) {
//                if (startIndex == DEFAULT_INDEX) {
//                    startIndex = i;
//                }
//                if (event == Event.PURCHASE) {
//                    endIndex = i;
//                }
//
//            } else {
//                addNewJourney(journeys, userEvents, startIndex, endIndex);
//                startIndex = DEFAULT_INDEX;
//                endIndex = DEFAULT_INDEX;
//            }
//        }
//
//        addNewJourney(journeys, userEvents, startIndex, endIndex);
//
//        return journeys;
//    }

//    private void addNewJourney(List<Journey> journeys, List<Event> userEvents, int startIndex, int endIndex) {
//        if (endIndex != DEFAULT_INDEX) {
//            journeys.add(new Journey(userEvents.subList(startIndex, endIndex+1)));
//        }
//    }

    public static void main(String[] args) {
        EventListener eventListener = new EventListener(3);
//        List<Journey> journeys = report.retrieveSuccessfulJourneys(Arrays.asList(
//                Event.VIEW_PRODUCT, Event.ADD_TO_CART, Event.VIEW_PRODUCT, Event.ADD_TO_CART, Event.PURCHASE,
//                Event.VIEW_PAGE, Event.VIEW_PRODUCT, Event.ADD_TO_CART, Event.VIEW_PRODUCT, Event.ADD_TO_CART, Event.PURCHASE
//        ));
//        System.out.println(journeys);
//
//        List<Journey> journeys2 = report.retrieveSuccessfulJourneys(Arrays.asList());
//        System.out.println(journeys2);
//
//        List<Journey> journeys3 = report.retrieveSuccessfulJourneys(null);
//        System.out.println(journeys3);
//
//        List<Journey> journeys4 = report.retrieveSuccessfulJourneys(Arrays.asList(
//                Event.VIEW_PRODUCT, Event.ADD_TO_CART, Event.VIEW_PRODUCT, Event.ADD_TO_CART, Event.PURCHASE,
//                Event.VIEW_PAGE, Event.VIEW_PRODUCT, Event.ADD_TO_CART, Event.VIEW_PRODUCT, Event.ADD_TO_CART,
//                Event.PURCHASE, Event.ADD_TO_CART
//        ));
//        System.out.println(journeys4);
//
//        List<Journey> journeys5 = report.retrieveSuccessfulJourneys(Arrays.asList(
//                Event.VIEW_PRODUCT, Event.ADD_TO_CART, Event.VIEW_PRODUCT, Event.ADD_TO_CART, Event.PURCHASE,
//                Event.VIEW_PAGE, Event.VIEW_PRODUCT, Event.ADD_TO_CART, Event.VIEW_PRODUCT, Event.ADD_TO_CART,
//                Event.PURCHASE, Event.ADD_TO_CART, Event.LOGOUT
//        ));
//        System.out.println(journeys5);

//        List<Journey> journeys6 = eventListener.retrieveKLongestSuccessfulJourneys(Arrays.asList(
//                EventType.VIEW_PRODUCT, EventType.ADD_TO_CART, EventType.VIEW_PRODUCT, EventType.ADD_TO_CART, EventType.PURCHASE,
//                EventType.VIEW_PAGE, EventType.VIEW_PRODUCT, EventType.ADD_TO_CART, EventType.VIEW_PRODUCT, EventType.ADD_TO_CART,
//                EventType.PURCHASE, EventType.ADD_TO_CART, EventType.LOGOUT
//        ), 2);
//        System.out.println(journeys6);
//
//        List<Journey> journeys7 = eventListener.retrieveKLongestSuccessfulJourneys(Arrays.asList(
//                EventType.VIEW_PRODUCT, EventType.ADD_TO_CART, EventType.VIEW_PRODUCT, EventType.ADD_TO_CART, EventType.PURCHASE,
//                EventType.VIEW_PAGE, EventType.VIEW_PRODUCT, EventType.ADD_TO_CART, EventType.VIEW_PRODUCT, EventType.ADD_TO_CART,
//                EventType.PURCHASE, EventType.ADD_TO_CART, EventType.LOGOUT,
//                EventType.VIEW_PRODUCT, EventType.ADD_TO_CART, EventType.VIEW_PRODUCT, EventType.ADD_TO_CART, EventType.PURCHASE,
//                EventType.VIEW_PAGE, EventType.VIEW_PRODUCT, EventType.ADD_TO_CART, EventType.VIEW_PRODUCT, EventType.ADD_TO_CART,
//                EventType.PURCHASE, EventType.ADD_TO_CART, EventType.LOGOUT
//        ), 3);
//        System.out.println(journeys7);
//
//        List<Journey> journeys8 = eventListener.retrieveKLongestSuccessfulJourneys(Arrays.asList(
//                EventType.VIEW_PRODUCT, EventType.ADD_TO_CART, EventType.VIEW_PRODUCT, EventType.ADD_TO_CART, EventType.PURCHASE,
//                EventType.VIEW_PAGE, EventType.VIEW_PRODUCT, EventType.ADD_TO_CART, EventType.VIEW_PRODUCT, EventType.ADD_TO_CART,
//                EventType.PURCHASE, EventType.ADD_TO_CART, EventType.LOGOUT,
//                EventType.VIEW_PRODUCT, EventType.ADD_TO_CART, EventType.VIEW_PRODUCT, EventType.ADD_TO_CART, EventType.PURCHASE,
//                EventType.VIEW_PAGE, EventType.VIEW_PAGE, EventType.VIEW_PRODUCT, EventType.ADD_TO_CART, EventType.VIEW_PRODUCT, EventType.ADD_TO_CART,
//                EventType.PURCHASE, EventType.ADD_TO_CART, EventType.LOGOUT, EventType.LOGIN,
//                EventType.VIEW_PRODUCT, EventType.ADD_TO_CART, EventType.VIEW_PRODUCT, EventType.ADD_TO_CART, EventType.PURCHASE,
//                EventType.VIEW_PAGE, EventType.VIEW_PRODUCT, EventType.ADD_TO_CART, EventType.VIEW_PRODUCT, EventType.ADD_TO_CART,
//                EventType.PURCHASE, EventType.ADD_TO_CART, EventType.LOGOUT, EventType.VIEW_PRODUCT, EventType.ADD_TO_CART, EventType.VIEW_PRODUCT, EventType.ADD_TO_CART, EventType.PURCHASE,
//                EventType.VIEW_PAGE, EventType.VIEW_PRODUCT, EventType.ADD_TO_CART, EventType.ADD_TO_CART,
//                EventType.PURCHASE, EventType.ADD_TO_CART, EventType.LOGOUT
//        ), 4);
//        System.out.println(journeys8);
//
//        List<Journey> journeys9 = eventListener.retrieveKLongestSuccessfulJourneys(Arrays.asList(
//                EventType.VIEW_PRODUCT, EventType.ADD_TO_CART, EventType.VIEW_PRODUCT, EventType.ADD_TO_CART, EventType.PURCHASE,
//                EventType.VIEW_PAGE, EventType.VIEW_PRODUCT, EventType.ADD_TO_CART,
//                EventType.PURCHASE, EventType.ADD_TO_CART, EventType.LOGOUT,
//                EventType.VIEW_PRODUCT, EventType.ADD_TO_CART, EventType.VIEW_PRODUCT, EventType.PURCHASE,
//                EventType.VIEW_PAGE, EventType.VIEW_PAGE, EventType.VIEW_PRODUCT, EventType.ADD_TO_CART, EventType.VIEW_PRODUCT,
//                EventType.PURCHASE, EventType.ADD_TO_CART, EventType.LOGOUT, EventType.LOGIN,
//                EventType.VIEW_PRODUCT, EventType.PURCHASE,
//                EventType.VIEW_PAGE, EventType.ADD_TO_CART,
//                EventType.PURCHASE, EventType.ADD_TO_CART, EventType.LOGOUT, EventType.VIEW_PRODUCT, EventType.ADD_TO_CART, EventType.VIEW_PRODUCT, EventType.ADD_TO_CART, EventType.PURCHASE,
//                EventType.VIEW_PAGE, EventType.VIEW_PRODUCT, EventType.ADD_TO_CART, EventType.ADD_TO_CART,
//                EventType.PURCHASE, EventType.ADD_TO_CART, EventType.LOGOUT
//        ), 4);
//        System.out.println(journeys9);
//
//        List<Journey> journeys10 = eventListener.retrieveKLongestSuccessfulJourneys(Arrays.asList(
//                EventType.VIEW_PRODUCT, EventType.ADD_TO_CART, EventType.VIEW_PRODUCT, EventType.ADD_TO_CART, EventType.PURCHASE,
//                EventType.VIEW_PAGE, EventType.VIEW_PRODUCT, EventType.ADD_TO_CART,
//                EventType.PURCHASE, EventType.ADD_TO_CART, EventType.LOGOUT,
//                EventType.VIEW_PRODUCT, EventType.ADD_TO_CART, EventType.VIEW_PRODUCT, EventType.PURCHASE,
//                EventType.VIEW_PAGE, EventType.VIEW_PAGE, EventType.VIEW_PRODUCT, EventType.ADD_TO_CART, EventType.VIEW_PRODUCT,
//                EventType.PURCHASE, EventType.ADD_TO_CART, EventType.LOGOUT, EventType.LOGIN,
//                EventType.VIEW_PRODUCT, EventType.PURCHASE,
//                EventType.VIEW_PAGE, EventType.ADD_TO_CART,
//                EventType.PURCHASE, EventType.ADD_TO_CART, EventType.LOGOUT, EventType.VIEW_PRODUCT, EventType.ADD_TO_CART, EventType.VIEW_PRODUCT, EventType.ADD_TO_CART, EventType.PURCHASE,
//                EventType.VIEW_PAGE, EventType.VIEW_PRODUCT, EventType.ADD_TO_CART, EventType.ADD_TO_CART,
//                EventType.PURCHASE, EventType.ADD_TO_CART, EventType.LOGOUT
//        ), 14);
//        System.out.println(journeys10);
//
//        List<Journey> journeys11 = eventListener.retrieveKLongestSuccessfulJourneys(Arrays.asList(
//                EventType.VIEW_PRODUCT, EventType.ADD_TO_CART, EventType.VIEW_PRODUCT, EventType.ADD_TO_CART,
//                EventType.VIEW_PRODUCT, EventType.ADD_TO_CART, EventType.VIEW_PRODUCT, EventType.ADD_TO_CART,
//                EventType.VIEW_PAGE, EventType.VIEW_PRODUCT, EventType.ADD_TO_CART,
//                EventType.PURCHASE, EventType.ADD_TO_CART, EventType.LOGOUT,
//                EventType.VIEW_PRODUCT, EventType.ADD_TO_CART, EventType.VIEW_PRODUCT, EventType.PURCHASE,
//                EventType.VIEW_PAGE, EventType.VIEW_PAGE, EventType.VIEW_PRODUCT, EventType.ADD_TO_CART, EventType.VIEW_PRODUCT,
//                EventType.PURCHASE, EventType.ADD_TO_CART, EventType.LOGOUT, EventType.LOGIN,
//                EventType.VIEW_PRODUCT, EventType.PURCHASE,
//                EventType.VIEW_PAGE, EventType.ADD_TO_CART,
//                EventType.PURCHASE, EventType.ADD_TO_CART, EventType.LOGOUT, EventType.VIEW_PRODUCT, EventType.ADD_TO_CART, EventType.VIEW_PRODUCT, EventType.ADD_TO_CART, EventType.PURCHASE,
//                EventType.VIEW_PAGE, EventType.VIEW_PRODUCT, EventType.ADD_TO_CART, EventType.ADD_TO_CART,
//                EventType.PURCHASE, EventType.ADD_TO_CART, EventType.LOGOUT
//        ), 4);
//        System.out.println(journeys11);
    }

}
