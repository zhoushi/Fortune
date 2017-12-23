package com.borderXLab.resources;

import com.borderXLab.service.FortuneService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by zhou on 2017/12/23.
 */
@Produces(MediaType.APPLICATION_JSON)
public class FortuneResource {

    private FortuneService fortuneService;

    public FortuneResource (FortuneService fortuneService){
        this.fortuneService = fortuneService;
    }

    @POST
    @Path("/fortunes")
    public long addFortunes(String message){

        return fortuneService.create(message);
    }

    @GET
    @Path("/fortune")
    public String getFortune(){
        return fortuneService.find();
    }

    @DELETE
    @Path("/fortunes/{fortuneId}")
    public boolean deleteFortunes(long fortuneId){
        return fortuneService.delete(fortuneId);
    }

}
