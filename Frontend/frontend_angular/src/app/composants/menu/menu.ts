import { Component } from '@angular/core';
import {SMenu} from './smenu';
import {NgClass, NgForOf, NgIf} from '@angular/common';
import {Router} from '@angular/router';
import {of} from 'rxjs';

@Component({
  selector: 'app-menu',
  imports: [
    NgForOf,
    NgClass,
    NgIf
  ],
  templateUrl: './menu.html',
  styleUrl: './menu.css'
})
export class Menu {
  public menuProperties: Array<SMenu> = [
    {
      id: '1',
      titre: 'Tableau de bord',
      icon: 'fas fa-chart-line',
      url: '',
      expanded: false,
      sousMenu: [
        {
          id: '11',
          titre: 'Vue d\'ensemble',
          icon: 'fas fa-chart-pie',
          url: ''
        },
        {
          id: '12',
          titre: 'Statistiques',
          icon: 'fas fa-chart-bar',
          url: 'statistiques'
        }
      ]
    },
    {
      id: '2',
      titre: 'Articles',
      icon: 'fas fa-boxes',
      url: '',
      expanded: false,
      sousMenu: [
        {
          id: '21',
          titre: 'Articles',
          icon: 'fas fa-boxes',
          url: 'articles'
        },
        {
          id: '22',
          titre: 'Mouvements du stock',
          icon: 'fab fa-stack-overflow',
          url: 'mvtstk'
        }
      ]
    },
    {
      id: '3',
      titre: 'Clients',
      icon: 'fas fa-users',
      url: '',
      expanded: false,
      sousMenu: [
        {
          id: '31',
          titre: 'Clients',
          icon: 'fas fa-users',
          url: 'clients'
        },
        {
          id: '32',
          titre: 'Commandes clients',
          icon: 'fas fa-shopping-basket',
          url: 'commandesclient'
        }
      ]
    },
    {
      id: '4',
      titre: 'Fournisseurs',
      icon: 'fas fa-users',
      url: '',
      expanded: false,
      sousMenu: [
        {
          id: '41',
          titre: 'Fournisseurs',
          icon: 'fas fa-users',
          url: 'fournisseurs'
        },
        {
          id: '42',
          titre: 'Commandes fournisseurs',
          icon: 'fas fa-truck',
          url: 'commandesfournisseur'
        }
      ]
    },
    {
      id: '5',
      titre: 'Parametrages',
      icon: 'fas fa-cogs',
      url: '',
      expanded: false,
      sousMenu: [
        {
          id: '51',
          titre: 'Categories',
          icon: 'fas fa-tools',
          url: 'categories'
        },
        {
          id: '52',
          titre: 'Utilisateurs',
          icon: 'fas fa-users-cog',
          url: 'utilisateurs'
        },
        {
          id: '53',
          titre: 'Rôles',
          icon: 'fas fa-user-tag',
          url: 'roles'
        }
      ]
    }
  ]

  private lastSelectedMenu: SMenu | undefined
    constructor(
      private router: Router
    ) {
    }
  navigate(menu: SMenu): void {
    if(this.lastSelectedMenu){
      this.lastSelectedMenu.active = false
    }
    menu.active = true
    this.router.navigate([menu.url])
    this.lastSelectedMenu = menu
  }

  toggleMenu(menu: any): void {
    // Fermer tous les autres menus
    this.menuProperties.forEach(m => {
      if (m.id !== menu.id) {
        m.expanded = false
      }
    })
    // Toggle le menu cliqué
    menu.expanded = !menu.expanded
  }

  getMenuGradient(index: number): string {
    const gradients = [
      'from-primary-400 to-primary-600',
      'from-primary-500 to-primary-700', 
      'from-primary-300 to-primary-500',
      'from-primary-600 to-primary-800',
      'from-primary-400 to-primary-700'
    ]
    return gradients[index % gradients.length]
  }

  protected readonly of = of;
}
