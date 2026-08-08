package org.gd.eventhub.Services;

import lombok.RequiredArgsConstructor;

import org.gd.eventhub.Entity.Venue;
import org.gd.eventhub.Exceptions.ResourceNotFoundException;

import org.gd.eventhub.Mapper.VenueMapper;

import org.gd.eventhub.Repository.VenueRepository;
import org.gd.eventhub.dto.Requests.VenueRequest;
import org.gd.eventhub.dto.Response.VenueResponse;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VenueService {

    private final VenueMapper venueMapper;
    private final VenueRepository venueRepository;


    public  VenueResponse createVenue(VenueRequest request) {
        Venue venue = venueMapper.toEntity(request);
        Venue savedVenue = venueRepository.save(venue);

        return venueMapper.toResponse(savedVenue);

    }

    public  VenueResponse getVenueById(Integer id) {
        Venue venue = venueRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("venue not found"));

        return venueMapper.toResponse(venue);

    }

    public  List<VenueResponse> getAllVenues(Pageable pageable) {
        return venueRepository.findAll(pageable)
                .stream()
                .map(venueMapper::toResponse)
                .toList();
    }

    public  VenueResponse updateVenue(Integer id, VenueRequest request) {
        Venue venue = venueRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("venue not found"));
        venueMapper.updateVenue(request, venue);
        venueRepository.save(venue);
        return venueMapper.toResponse(venue);
    }

    public  VenueResponse deleteVenue(Integer id) {
        Venue venue = venueRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("venue not found"));
        venueRepository.delete(venue);
        return venueMapper.toResponse(venue);
    }


}





