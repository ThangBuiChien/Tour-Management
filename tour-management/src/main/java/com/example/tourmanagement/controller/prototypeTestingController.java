package com.example.tourmanagement.controller;

import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

public class prototypeTestingController {
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private TourService tourService;

    @InjectMocks
    private TourController tourController;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Test
    public void testAddRoute() throws Exception {
        // Initialize mocks
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        // Mock the prototype and its behavior
        Tour prototypeTour = new Tour();
        prototypeTour.setTourName("Sample Tour");
        prototypeTour.setDetailRoute(new DetailRoute());
        prototypeTour.setLengthTrip(5);
        prototypeTour.setTourDescription("Sample Description");
        prototypeTour.setTourPrice(100.0f);
        prototypeTour.setTourCapacity(new Capacity());
        prototypeTour.setTourGuide(new TourGuide());
        when(tourService.generateNewTourFromPrototype(any(Tour.class))).thenReturn(prototypeTour);

        // Define the request parameters
        LocalDate date1 = LocalDate.of(2023, 6, 1);
        LocalDate date2 = LocalDate.of(2023, 6, 2);

        // Perform the request
        mockMvc.perform(post("/tour/add")
                        .param("startDates", date1.toString(), date2.toString())
                        .flashAttr("tour", prototypeTour))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/tour/sorting"));

        // Verify the service method was called the expected number of times
        verify(tourService, times(2)).saveTour(any(Tour.class));
    }
}
