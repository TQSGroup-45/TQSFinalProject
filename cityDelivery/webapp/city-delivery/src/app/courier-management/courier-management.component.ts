import { Component, OnInit, ViewChild } from '@angular/core';
import Map from 'ol/Map';
import View from 'ol/View';
import Feature from 'ol/Feature';
import Point from 'ol/geom/Point';
import { fromLonLat } from 'ol/proj.js';
import {Tile as TileLayer, Vector as VectorLayer} from 'ol/layer';
import VectorSource from 'ol/source/Vector';
import {Icon, Style} from 'ol/style';
import OSM from 'ol/source/OSM';
import { Geometry } from 'ol/geom';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';

@Component({
  selector: 'app-courier-management',
  templateUrl: './courier-management.component.html',
  styleUrls: ['./courier-management.component.css']
})

export class CourierManagementComponent implements OnInit {

  map: Map;
  some: Feature;
  some2: Feature;
  some3: Feature;
  some4: Feature;
  vectorSource: VectorSource;
  vectorLayer: VectorLayer<VectorSource<Geometry>>;

  displayedColumns: string[] = ['name', 'email', 'contact', 'profile', 'answer'];

  couriersList = new MatTableDataSource<object>( [
    {name: "José António", email:"joseant@amail.com", contact: 900000000},
    {name: "Manuel Oliveira", email:"moliv@amail.com", contact: 900000001}
  ]);

  @ViewChild(MatPaginator) paginator: MatPaginator;

  ngAfterViewInit() {
    this.couriersList.paginator = this.paginator;
  }

  ngOnInit(): void {
    this.some = new Feature({
      geometry: new Point(fromLonLat([-8.659163,40.633109]))
    });

    this.some.setStyle(new Style({
      image: new Icon(({
        color: '#8959A8',
        crossOrigin: 'anonymous',
        src: 'assets/vectorpoint.svg',
        imgSize: [75,75 ]
      }))
    }))

    this.some2 = new Feature({
      geometry: new Point(fromLonLat([-8.652402,40.631077]))
    });

    this.some2.setStyle(new Style({
      image: new Icon(({
        color: '#8959A8',
        crossOrigin: 'anonymous',
        src: 'assets/vectorpoint.svg',
        imgSize: [75,75 ]
      }))
    }))

    this.some3 = new Feature({
      geometry: new Point(fromLonLat([-8.657577,40.634268]))
    });

    this.some3.setStyle(new Style({
      image: new Icon(({
        color: '#8959A8',
        crossOrigin: 'anonymous',
        src: 'assets/vectorpoint.svg',
        imgSize: [75,75 ]
      }))
    }))

    this.some4 = new Feature({
      geometry: new Point(fromLonLat([-8.650600,40.634692]))
    });

    this.some4.setStyle(new Style({
      image: new Icon(({
        color: '#8959A8',
        crossOrigin: 'anonymous',
        src: 'assets/vectorpoint.svg',
        imgSize: [75,75 ]
      }))
    }))

    this.vectorSource = new VectorSource({
      features: [this.some,this.some2,this.some3,this.some4]
    });

    this.vectorLayer = new VectorLayer({
      source: this.vectorSource
    });

    this.map = new Map({
        target: 'ol-map',
        layers: [ new TileLayer({
          source: new OSM()
        }), this.vectorLayer ],
        view: new View({
          center: fromLonLat([-8.659163,40.633109]),
          zoom: 17
        })
      });

  }
}
