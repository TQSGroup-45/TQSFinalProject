import { Component, OnInit } from '@angular/core';
import { NgbCarouselConfig } from '@ng-bootstrap/ng-bootstrap';

@Component({
    selector: 'app-carousel',
    templateUrl: './carousel.component.html',
    styleUrls: ['./carousel.component.css'],
    providers: [NgbCarouselConfig] 
})
export class CarouselComponent {


    images = [`994523/pexels-photo-994523.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260`,
               `325876/pexels-photo-325876.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260`,
                `996329/pexels-photo-996329.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2`
            ].map(  (title) => `https://images.pexels.com/photos/${title}` );

    constructor(config: NgbCarouselConfig) {
      config.interval = 10000;
      config.wrap = false;
      config.keyboard = false;
      config.pauseOnHover = false;
    }
}