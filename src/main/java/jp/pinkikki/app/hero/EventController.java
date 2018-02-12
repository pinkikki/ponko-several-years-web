package jp.pinkikki.app.hero;

import jp.pinkikki.domain.hero.model.Event;
import jp.pinkikki.domain.hero.model.SceneName;
import jp.pinkikki.domain.hero.service.EventService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Locale;

@Controller
@RequestMapping("hero")
public class EventController {

    @Autowired
    EventService eventService;

    @Autowired
    MessageSource messageSource;

    @Autowired
    ConversionService conversionService;

    @ModelAttribute
    public EventForm setupForm() {
        return new EventForm();
    }

    @GetMapping(path = "events")
    String listEvents(Model model, @Validated EventForm eventForm, BindingResult result) {
        if (result.hasErrors()) {
            return "hero/events";
        }
        model.addAttribute("events", eventService.findByEventId(eventForm.getEventId()));
        return "hero/events";
    }

    @GetMapping(path = "input")
    String input() {
        return "hero/input";
    }

    @PostMapping(path = "confirm")
    String confirm(@Validated(EventForm.EventInput.class) EventForm eventForm, BindingResult result) {
        if (result.hasErrors()) {
            return "hero/input";
        }
        return "hero/confirm";
    }

    @PostMapping(path = "decide")
    String decide(@Validated(EventForm.EventInput.class) EventForm eventForm, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            return "hero/input";
        }
        Event event = new Event();
        BeanUtils.copyProperties(eventForm, event);
        // String　→　SceneNameの変換は、ControllerAdviceで実施
//        event.setSceneName(conversionService.convert(eventForm.getSceneName(), SceneName.class));
        eventService.save(event);
        attributes.addFlashAttribute("message", messageSource.getMessage("complete.message", new String[]{"イベント登録"}, Locale.JAPAN));
        return "redirect:complete";
    }

    @GetMapping(path = "complete")
    String complete() {
        return "hero/complete";
    }
}
