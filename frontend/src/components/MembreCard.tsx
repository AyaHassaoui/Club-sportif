import React from 'react'

interface Membre {
  id: number
  nom: string
  categorie: string
  dateAdhesion: string
}

interface MembreCardProps {
  membre: Membre
}

export function MembreCard({ membre }: MembreCardProps) {
  const getCategorieBadgeClass = (categorie: string) => {
    switch (categorie) {
      case 'Adulte':
        return 'bg-primary'
      case 'Junior':
        return 'bg-success'
      case 'Sénior':
        return 'bg-info'
      default:
        return 'bg-secondary'
    }
  }

  return (
    <div className="col-md-4 mb-3">
      <div className="card h-100">
        <div className="card-body">
          <h5 className="card-title">{membre.nom}</h5>
          <p className="card-text">
            <span className={`badge ${getCategorieBadgeClass(membre.categorie)} me-2`}>
              {membre.categorie}
            </span>
            <br/>
            <strong>Date d'adhésion:</strong> {new Date(membre.dateAdhesion).toLocaleDateString()}
          </p>
        </div>
      </div>
    </div>
  )
}
