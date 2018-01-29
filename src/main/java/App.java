
import models.*;
import dao.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static spark.Spark.*;

import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;


public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");
        String connectionString = "jdbc:h2:~/todolist.db;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        Sql2oArtistDao artistDao = new Sql2oArtistDao(sql2o);
        Sql2oCdDao cdDao = new Sql2oCdDao(sql2o);


        //GET: show New CD form
        get("/cds/new", (req, res)->{
            Map<String, Object> model = new HashMap<>();

            List<CD> allCDs = cdDao.getAllCDsByArtist(Integer.parseInt(req.queryParams("artistid")));
            model.put("allCDs", allCDs);

            return new ModelAndView(model, "cd-form.hbs");
        }, new HandlebarsTemplateEngine());

        //POST: process New CD form
        post("/cds/new", (req, res)->{
            Map<String, Object> model = new HashMap<>();
            String album = req.queryParams("album");
            int artistId = Integer.parseInt(req.queryParams("artistId"));
            CD newCD = new CD(album, artistId);
            cdDao.add(newCD);

            List<CD> cds = cdDao.getAll();
            model.put("cds", cds);

            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        //GET: delete all CD's
        get("/cds/delete", (req, res)->{
            Map<String, Object> model = new HashMap<>();
            cdDao.clearAll();
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        //GET: delete all artists and all CDs
        get("/artists/delete", (req, res)->{
            Map<String, Object> model = new HashMap<>();
            artistDao.clearAll();
            cdDao.clearAll();
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        //GET: show Update Artist name form
        get("/artists/update", (req, res)->{
            Map<String, Object> model = new HashMap<>();

            model.put("editCD", true);
            List<Artist> artists = artistDao.getAll();
            model.put("artists", artists);

            return new ModelAndView(model, "artist-form.hbs");
        }, new HandlebarsTemplateEngine());

        //POST: process Update Artist form
        post("/artist/update", (req, res)->{
            Map<String, Object> model = new HashMap<>();

            int idOfArtistToEdit = Integer.parseInt(req.queryParams("editArtistId"));
            String newName = req.queryParams("newArtistName");
            artistDao.update(artistDao.findById(idOfArtistToEdit).getId(), newName);

            List<Artist> artists = artistDao.getAll();
            model.put("artists", artists);

            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        //GET: show New Artist form
        get("/artists/new", (req, res)->{
            Map<String, Object> model = new HashMap<>();

            List<Artist> artists = artistDao.getAll();
            model.put("artists", artists);

            return new ModelAndView(model, "artist-form.hbs");
        }, new HandlebarsTemplateEngine());

        //POST: process New Artist form
        post("/artists/new", (req, res)->{
            Map<String, Object> model = new HashMap<>();
            String artist = req.queryParams("artist");
            Artist newArtist = new Artist(artist);
            artistDao.add(newArtist);

            List<Artist> artists = artistDao.getAll();
            model.put("artists", artists);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        //GET: show all artists
        get("/artists", (req, res)->{
            Map<String, Object> model = new HashMap<>();

            List<Artist> allArtists = artistDao.getAll();
            model.put("artists", allArtists);

            return new ModelAndView(model, "artists.hbs");
        }, new HandlebarsTemplateEngine());

        //GET: show specific artist and associated CDs
        get("/artists/:artist_id", (req, res)->{
            Map<String, Object> model = new HashMap<>();
            int artistId = Integer.parseInt(req.queryParams("artist_id"));
            model.put("foundArtist", artistDao.findById(artistId));
            model.put("foundCDs", cdDao.getAllCDsByArtist(artistId));

            return new ModelAndView(model, "artist-detail.hbs");
        }, new HandlebarsTemplateEngine());

        //GET: show individual CD
        get("/artists/:artist_id/cds/:cd_id", (req, res)->{
            Map<String, Object> model = new HashMap<>();
            int cdId = Integer.parseInt(req.queryParams("cd_id"));
            CD foundCD = cdDao.findById(cdId);
            model.put("foundCD", foundCD.getTitle());
            return new ModelAndView(model, "cd-detail.hbs");
        }, new HandlebarsTemplateEngine());

        //GET: show CD Update form
        get("/artists/:artist_id/cds/:cd_id/update", (req, res)->{
            Map<String, Object> model = new HashMap<>();

            model.put("editCD", true);

            List<CD> allCDs = cdDao.getAll();
            model.put("cds", allCDs);

            return new ModelAndView(model, "cd-form.hbs");
        }, new HandlebarsTemplateEngine());

        //POST: process CD Update form
        post("/artists/:artist_id/cds/:cd_id/update", (req, res)->{
            Map<String, Object> model = new HashMap<>();
            int idOfCDToEdit = Integer.parseInt(req.queryParams("editCDId"));
            String newTitle = req.queryParams("newCDName");
            cdDao.update(cdDao.findById(idOfCDToEdit).getId(), newTitle);

            List<CD> cds = cdDao.getAll();
            model.put("cds", cds);

            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        //GET: delete a specific CD
        get("/artists/:artist_id/cds/:cd_id/delete", (req, res)->{
            Map<String, Object> model = new HashMap<>();
            int cdId = Integer.parseInt(req.queryParams("cd_id"));
            cdDao.deleteById(cdId);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        //GET: show all artists and all CDs
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();

            List<Artist> allArtists = artistDao.getAll();
            model.put("artists", allArtists);

            List<CD> allCds = cdDao.getAll();
            model.put("cds", allCds);

            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());
    }

}
